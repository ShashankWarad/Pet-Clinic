package com.technoboost.pet_clinic.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TypesDto {
    private Long typesId;

    private String name;

    public TypesDto(Long typesId, String name) {
        this.typesId = typesId;
        this.name = name;
    }
}
