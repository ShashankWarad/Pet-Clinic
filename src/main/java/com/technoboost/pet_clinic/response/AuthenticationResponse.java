package com.technoboost.pet_clinic.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String authenticationToken;
    private String username;
    private String tokenType = "Bearer";
    private String name;

    public AuthenticationResponse(String authenticationToken, String username) {
        this.authenticationToken = authenticationToken;
        this.username = username;
    }
}