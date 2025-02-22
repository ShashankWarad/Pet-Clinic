package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.payload.PetsCreatePayload;
import com.technoboost.pet_clinic.app.payload.PetsUpdatePayload;
import com.technoboost.pet_clinic.app.response.PetsResponse;
import com.technoboost.pet_clinic.response.ApiResponse;

public interface PetsService {
    ApiResponse createPets(PetsCreatePayload payload);

    ApiResponse updatePets(PetsUpdatePayload payload);

    ApiResponse deletePets(Long id);

    PetsResponse getAllPets();
}
