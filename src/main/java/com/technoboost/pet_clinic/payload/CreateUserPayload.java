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
    private String userName;
    private String password;
    private String mobileNumber;
}
