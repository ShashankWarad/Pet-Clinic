package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.payload.PetsCreatePayload;
import com.technoboost.pet_clinic.app.payload.PetsUpdatePayload;
import com.technoboost.pet_clinic.app.response.PetsResponse;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.security.UserPrincipal;

public interface PetsService {
    ApiResponse createPets(PetsCreatePayload payload, UserPrincipal userPrincipal);

    ApiResponse updatePets(PetsUpdatePayload payload, UserPrincipal userPrincipal);

    ApiResponse deletePets(Long id);

    PetsResponse getAllPets();
}
