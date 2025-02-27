package com.technoboost.pet_clinic.app.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetsCreatePayload {
    private String name;

    private LocalDate birthDate;

    private Long typeId;

    private Long ownerId;

}
