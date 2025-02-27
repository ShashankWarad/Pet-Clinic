package com.technoboost.pet_clinic.app.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeUpdatePayload {
    private Long id;

    private String name;
}
