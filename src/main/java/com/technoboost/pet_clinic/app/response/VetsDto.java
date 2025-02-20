package com.technoboost.pet_clinic.app.response;

import com.technoboost.pet_clinic.app.model.Specialties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class VetsDto {

    private String vetName;
    private List <SpecialtiesDto> specialties;
}
