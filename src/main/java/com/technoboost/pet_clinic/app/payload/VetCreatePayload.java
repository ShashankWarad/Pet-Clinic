package com.technoboost.pet_clinic.app.payload;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class VetCreatePayload {
    private String firstName;
    private String lastName;
    private List<String> specialtiesName;
}
