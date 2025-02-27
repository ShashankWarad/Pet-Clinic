package com.technoboost.pet_clinic.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OwnerDto {
    private Long id;

    private String name;

    private String email;

    private String mobileNumber;

    private String address;

    private String city;
}
