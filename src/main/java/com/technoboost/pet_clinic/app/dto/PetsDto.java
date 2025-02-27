package com.technoboost.pet_clinic.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PetsDto {
    private Long id;

    private String name;

    private String DOB;

    private TypesDto petTypes;

    private OwnerDto ownerDto;
}
