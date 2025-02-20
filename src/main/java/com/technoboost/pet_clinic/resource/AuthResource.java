package com.technoboost.pet_clinic.resource;

import com.technoboost.pet_clinic.payload.CreateUserPayload;
import com.technoboost.pet_clinic.payload.LoginPayload;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthResource {
    private final AuthService authService;

    @PostMapping("sign-up")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserPayload payload){

        return ResponseEntity
                .ok(authService.signUp(payload));

    }

    @PostMapping("login")
    public ResponseEntity<?> logIn(@RequestBody LoginPayload payload){

        return ResponseEntity
                .ok(authService.logIn(payload));

    }
}
