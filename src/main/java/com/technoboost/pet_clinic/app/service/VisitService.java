package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.payload.VisitsCreatePayload;
import com.technoboost.pet_clinic.app.response.VisitResponse;
import com.technoboost.pet_clinic.security.UserPrincipal;

public interface VisitService {

    void createVisit(VisitsCreatePayload payload, UserPrincipal userPrincipal);

    VisitResponse getAllVisit( UserPrincipal userPrincipal);

    void deleteVisit(Long id, UserPrincipal userPrincipal);
}
