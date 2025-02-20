package com.technoboost.pet_clinic.app.service;


import com.technoboost.pet_clinic.app.response.VetsDto;
import com.technoboost.pet_clinic.app.payload.VetCreatePayload;
import com.technoboost.pet_clinic.app.response.VetsResponse;
import com.technoboost.pet_clinic.response.ApiResponse;

import java.util.List;

public interface VetsService {

    ApiResponse createVet(VetCreatePayload vetCreatePayload);

    VetsResponse getAllVets();
}
