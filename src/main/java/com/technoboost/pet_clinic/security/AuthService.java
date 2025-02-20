package com.technoboost.pet_clinic.security;

import com.technoboost.pet_clinic.payload.CreateUserPayload;
import com.technoboost.pet_clinic.payload.LoginPayload;
import com.technoboost.pet_clinic.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> logIn(LoginPayload payload);

    ApiResponse signUp(CreateUserPayload payload);
}
