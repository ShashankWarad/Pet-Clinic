package com.technoboost.pet_clinic.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PetsDto {
    private Long id;

    private String name;

    private String DOB;

    private List<TypesDto> petTypes;

    private OwnerDto ownerDto;
}
