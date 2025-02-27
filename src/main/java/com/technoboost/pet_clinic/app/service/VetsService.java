package com.technoboost.pet_clinic.app.service;


import com.technoboost.pet_clinic.app.payload.VetCreatePayload;
import com.technoboost.pet_clinic.app.response.VetsResponse;
import com.technoboost.pet_clinic.response.ApiResponse;

public interface VetsService {

    ApiResponse createVet(VetCreatePayload vetCreatePayload);

    VetsResponse getAllVets();
}
