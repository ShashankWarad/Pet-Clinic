package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.model.Pets;
import com.technoboost.pet_clinic.app.payload.PetsCreatePayload;
import com.technoboost.pet_clinic.app.payload.PetsUpdatePayload;
import com.technoboost.pet_clinic.app.response.PetsResponse;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.security.UserPrincipal;

import java.util.Optional;

public interface PetsService {
    ApiResponse createPets(PetsCreatePayload payload);

    ApiResponse updatePets(PetsUpdatePayload payload, UserPrincipal userPrincipal);

    ApiResponse deletePets(Long id);

    PetsResponse getAllPets();

    Pets getPetByName(String name);

    Optional<Pets> getPetById(Long petId);
}
