package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.payload.TypeCreatePayload;
import com.technoboost.pet_clinic.app.payload.TypeUpdatePayload;
import com.technoboost.pet_clinic.app.response.TypesResponse;
import com.technoboost.pet_clinic.response.ApiResponse;

public interface TypesService {
    ApiResponse createTypes(TypeCreatePayload payload);

    ApiResponse updateTypes(TypeUpdatePayload payload);

    ApiResponse deleteTypes(Long id);

    TypesResponse getAllTypes();


}
