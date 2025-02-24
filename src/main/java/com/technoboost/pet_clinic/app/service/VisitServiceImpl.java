package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.model.Pets;
import com.technoboost.pet_clinic.app.model.Vets;
import com.technoboost.pet_clinic.app.model.Visits;
import com.technoboost.pet_clinic.app.payload.VisitsCreatePayload;
import com.technoboost.pet_clinic.app.repository.PetsRepository;
import com.technoboost.pet_clinic.app.repository.VetsRepository;
import com.technoboost.pet_clinic.app.repository.VisitsRepository;
import com.technoboost.pet_clinic.app.response.VisitDto;
import com.technoboost.pet_clinic.app.response.VisitResponse;
import com.technoboost.pet_clinic.exception.PetClinicException;
import com.technoboost.pet_clinic.model.User;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.security.UserPrincipal;
import com.technoboost.pet_clinic.util.UtilMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitsRepository visitsRepository;
    private final PetsRepository petsRepository;
    private final VetsRepository vetsRepository;


    @Override
    public void createVisit(VisitsCreatePayload payload, UserPrincipal userPrincipal) {
//        User owner = Optional.ofNullable(userPrincipal.getUser())
//                .orElseThrow(() -> new PetClinicException("Un Authorized user"));

        Pets pet = petsRepository.findByIdAndActiveTrueAndDeletedFalse(payload.getPetId())
                .orElseThrow(() -> new PetClinicException("Pet not found " + payload.getPetId()));
        Vets vet = vetsRepository.findByIdAndActiveTrueAndDeletedFalse(payload.getVetId())
                .orElseThrow(() -> new PetClinicException("Pet not found " + payload.getVetId()));

        Visits visits = new Visits();
        visits.setPets(pet);
        visits.setVet(vet);
        visits.setOwner(new User(2L));
        visits.setVisitDate(payload.getVisitDate());
        visits.setDescription(payload.getDescription());
        visitsRepository.save(visits);
        ApiResponse.builder()
                .id(visits.getId())
                .message("visit added successfully!")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .success(true)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public VisitResponse getAllVisit(UserPrincipal userPrincipal) {
//        Long userId = Optional.ofNullable(userPrincipal.getUser().getId())
//                .orElseThrow(() -> new PetClinicException("Un Authorized user"));
        List<Visits> visitsList = visitsRepository.findAllByUserIdActiveTrueAndDeletedFalse(2L);
        if (visitsList.isEmpty()) {
            return VisitResponse.builder()
                    .visitedList(new ArrayList<>()).build();
        }
        List<VisitDto> visits = visitsList.stream().map(visit ->
                VisitDto.builder()
                        .id(visit.getId())
                        .petName(visit.getPets().getName())
                        .vetName(visit.getVet().getFirstName())
                        .visitDate(visit.getVisitDate())
                        .description(visit.getDescription())
                        .build()
        ).toList();
        return VisitResponse.builder()
                .visitedList(visits).build();

    }

    @Override
    public void deleteVisit(Long id, UserPrincipal userPrincipal) {
        //        User owner = Optional.ofNullable(userPrincipal.getUser())
//                .orElseThrow(() -> new PetClinicException("Un Authorized user"));
        Visits visits = visitsRepository.findByIdActiveTrueAndDeletedFalse(2L, id)
                .orElseThrow(() -> new PetClinicException("Visit Not found " + id));
        visits.setActive(false);
        visits.setDeleted(true);
        visitsRepository.save(visits);
    }
}
