package com.technoboost.pet_clinic.security;

import org.springframework.security.core.Authentication;

public interface JwtProvider {

    String generateToken(Authentication authentication);

    String getUsernameFromJWT(String token);

    boolean validateToken(String jwt);
}
