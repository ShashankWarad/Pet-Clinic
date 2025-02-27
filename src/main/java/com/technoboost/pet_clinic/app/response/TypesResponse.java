package com.technoboost.pet_clinic.app.response;

import com.technoboost.pet_clinic.app.dto.TypesDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TypesResponse {
    private List<TypesDto> petTypes;
}
