package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.model.Specialties;
import com.technoboost.pet_clinic.app.model.Vets;
import com.technoboost.pet_clinic.app.payload.VetCreatePayload;
import com.technoboost.pet_clinic.app.repository.SpecialtiesRepository;
import com.technoboost.pet_clinic.app.repository.VetsRepository;
import com.technoboost.pet_clinic.app.response.SpecialtiesDto;
import com.technoboost.pet_clinic.app.response.VetsDto;
import com.technoboost.pet_clinic.app.response.VetsResponse;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.util.UtilMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VetsServiceImpl implements VetsService {

    private final VetsRepository vetsRepository;
    private final SpecialtiesRepository specialtiesRepository;

    @Override
    public ApiResponse createVet(VetCreatePayload vetCreatePayload) {

        List<Specialties> specialties = vetCreatePayload.getSpecialtiesName()
                .stream()
                .map(name -> specialtiesRepository.saveAndFlush(new Specialties(name)))
                .toList();
        Vets vets = new Vets();
        vets.setFirstName(vetCreatePayload.getFirstName());
        vets.setLastName(vetCreatePayload.getLastName());
        vets.setSpecialties(specialties);
        vetsRepository.save(vets);
        return ApiResponse.builder()
                .id(vets.getId())
                .message("vet added successfully!")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .success(true)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public VetsResponse getAllVets() {
        List<Vets> vets = vetsRepository.findAllVetsAndActiveTrueAndDeletedFalse();

        List<VetsDto> vetsList = vets.stream().map(vet -> VetsDto.builder()
                .vetName(vet.getFirstName() + " " + vet.getLastName())
                .specialties(vet.getSpecialties().stream().map(specialties ->
                        SpecialtiesDto.builder()
                                .name(specialties.getName())
                                .build()).toList())
                .build()).toList();
        return VetsResponse.builder().vets(vetsList).build();
    }
}
