package com.technoboost.pet_clinic.app.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VisitDto {

    private Long id;

    private String petName;

    private String vetName;

    private LocalDateTime visitDate;

    private String description;
}
