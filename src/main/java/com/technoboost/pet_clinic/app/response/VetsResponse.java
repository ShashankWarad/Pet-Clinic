package com.technoboost.pet_clinic.app.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class VetsResponse {
    private List<VetsDto> vets;
}
