package com.technoboost.pet_clinic.app.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitsCreatePayload {
    private Long petId;

    private Long vetId;

    private LocalDateTime visitDate;

    private String description;
}
