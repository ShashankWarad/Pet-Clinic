package com.technoboost.pet_clinic.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginPayload {
    @NotBlank(message = "Username is required.")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters.")
    private String password;
}
