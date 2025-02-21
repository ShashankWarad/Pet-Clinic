package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.payload.PetsCreatePayload;
import com.technoboost.pet_clinic.app.payload.PetsUpdatePayload;
import com.technoboost.pet_clinic.app.response.PetsResponse;
import com.technoboost.pet_clinic.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetsServiceImpl implements PetsService{
    @Override
    public ApiResponse createPets(PetsCreatePayload payload) {
        return null;
    }

    @Override
    public ApiResponse updatePets(PetsUpdatePayload payload) {
        return null;
    }

    @Override
    public ApiResponse deletePets(Long id) {
        return null;
    }

    @Override
    public PetsResponse getAllPets() {
        return null;
    }
}
