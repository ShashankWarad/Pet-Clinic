package com.technoboost.pet_clinic.controller;

import com.technoboost.pet_clinic.payload.LoginPayload;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.response.AuthenticationResponse;
import com.technoboost.pet_clinic.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthService authService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@Valid LoginPayload loginPayload, Model model) {
        try {
            ResponseEntity<?> responseEntity = authService.logIn(loginPayload);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                AuthenticationResponse authenticationResponse = (AuthenticationResponse) responseEntity.getBody();
                model.addAttribute("message", "Login successful!");
                return "redirect:/api/auth/home";
            } else {
                ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
                model.addAttribute("error", apiResponse.getMessage());
                return "login";
            }
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }
}
