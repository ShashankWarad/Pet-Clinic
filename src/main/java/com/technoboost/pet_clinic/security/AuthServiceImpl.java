package com.technoboost.pet_clinic.security;

import com.technoboost.pet_clinic.exception.PetClinicException;
import com.technoboost.pet_clinic.model.Role;
import com.technoboost.pet_clinic.model.User;
import com.technoboost.pet_clinic.payload.CreateUserPayload;
import com.technoboost.pet_clinic.payload.LoginPayload;
import com.technoboost.pet_clinic.repository.RoleRepository;
import com.technoboost.pet_clinic.repository.UserRepository;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.response.AuthenticationResponse;
import com.technoboost.pet_clinic.util.UtilMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Lazy
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Lazy
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<?> logIn(LoginPayload payload) {
        log.error("Payload : " + payload.getUsername());
        Authentication authenticate;
        try {
            authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(payload.getUsername(),
                            payload.getPassword()));
        } catch (AuthenticationException e) {
            log.error("Login Error : {}", e.getMessage());
            return new ResponseEntity<>(ApiResponse.builder()
                    .id(0L)
                    .message(e.getMessage())
                    .path(UtilMethod.getPath())
                    .status(HttpStatus.NOT_FOUND.name())
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .success(false)
                    .timestamp(LocalDateTime.now().toString())
                    .build(), HttpStatus.OK);
        }

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        log.info("Token : " + authenticationToken);
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthenticationResponse response = new AuthenticationResponse(authenticationToken, payload.getUsername());
        response.setUsername(principal.getUser().getUsername());
        response.setName(principal.getUser().getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ApiResponse signUp(CreateUserPayload payload) {
        User user = new User();
        boolean b = userRepository.existsByUsernameAndEnabledTrue(payload.getUsername());
        if (b)
            throw new PetClinicException("Username already exist , PLease enter another email!");
        boolean c = userRepository.existsByMobileNumberAndEnabledTrue(payload.getMobileNumber());
        if (c)
            throw new PetClinicException("Mobile number already exist , PLease enter another Mobile number!");
        user.setName(payload.getName());
        user.setUsername(payload.getUsername());
        user.setMobileNumber(payload.getMobileNumber());
        user.setPassword(encodePassword(payload.getPassword()));
        user.setAddress(payload.getAddress());
        user.setCity(payload.getCity());
        Role role = roleRepository.findByName("ADMIN").
                orElseThrow(() -> new PetClinicException("Check role!"));
        user.setRoles(Collections.singleton(role));
        userRepository.saveAndFlush(user);
        return ApiResponse.builder()
                .id(user.getId())
                .path(UtilMethod.getPath())
                .message("Account created successfully")
                .status(HttpStatus.CREATED.name())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .timestamp(UtilMethod.currentDateTime())
                .build();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}