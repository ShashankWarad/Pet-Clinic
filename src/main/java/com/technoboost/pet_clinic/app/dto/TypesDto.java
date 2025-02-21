package com.technoboost.pet_clinic.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypesDto {
    private Long typesId;

    private String name;

    public TypesDto(Long typesId, String name) {
        this.typesId = typesId;
        this.name = name;
    }
}
