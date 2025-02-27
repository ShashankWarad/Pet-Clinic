package com.technoboost.pet_clinic.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserPayload {
    private String name;
    private String username;
    private String password;
    private String mobileNumber;
    private String address;
    private String city;
}
