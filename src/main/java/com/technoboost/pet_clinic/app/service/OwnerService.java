package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.response.OwnerResponse;
import jakarta.validation.Valid;


public interface OwnerService {
    void registerNewOwner(@Valid OwnerResponse ownerDTO);

}