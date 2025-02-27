package com.technoboost.pet_clinic.app.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VisitsUpdatePayload {
    private Long id;

    private Long PetId;

    private Long vetId;

    private LocalDate visitDate;

    private String description;
}
