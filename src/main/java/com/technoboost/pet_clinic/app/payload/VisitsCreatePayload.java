package com.technoboost.pet_clinic.app.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VisitsCreatePayload {
    private Long PetId;

    private LocalDate visitDate;

    private String description;
}
