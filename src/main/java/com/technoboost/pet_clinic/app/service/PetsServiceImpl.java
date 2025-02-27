package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.dto.OwnerDto;
import com.technoboost.pet_clinic.app.dto.PetsDto;
import com.technoboost.pet_clinic.app.dto.TypesDto;
import com.technoboost.pet_clinic.app.model.Pets;
import com.technoboost.pet_clinic.app.model.Types;
import com.technoboost.pet_clinic.app.payload.PetsCreatePayload;
import com.technoboost.pet_clinic.app.payload.PetsUpdatePayload;
import com.technoboost.pet_clinic.app.repository.PetsRepository;
import com.technoboost.pet_clinic.app.repository.TypesRepository;
import com.technoboost.pet_clinic.app.response.PetsResponse;
import com.technoboost.pet_clinic.exception.PetClinicException;
import com.technoboost.pet_clinic.model.User;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.security.UserPrincipal;
import com.technoboost.pet_clinic.util.UtilMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetsServiceImpl implements PetsService {
    private final UtilMethod utilMethod;
    private final PetsRepository petsRepository;

    private final TypesRepository typesRepository;

    @Override
    public ApiResponse createPets(PetsCreatePayload payload, UserPrincipal userPrincipal) {
        User user = utilMethod.checkPrincipal(userPrincipal);
        Types types = typesRepository.findByIdAndActiveTrueAndDeletedFalse(payload.getTypeId()).
                orElseThrow(() -> new PetClinicException("This types id is not exists , please insert an other id!"));
        Pets pets = new Pets();
        pets.setName(payload.getName());
        pets.setOwner(user);
        pets.setTypes(types);
        pets.setBirthDate(payload.getBirthDate());
        petsRepository.saveAndFlush(pets);
        return ApiResponse.builder()
                .id(pets.getId())
                .message("Pets created successfully!")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .success(true)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public ApiResponse updatePets(PetsUpdatePayload payload, UserPrincipal userPrincipal) {
        User user = utilMethod.checkPrincipal(userPrincipal);
        Pets pets = petsRepository.findByIdAndActiveTrueAndDeletedFalse(payload.getId())
                .orElseThrow(() -> new PetClinicException("Please Enter valid pet id!"));
        Types types = typesRepository.findByIdAndActiveTrueAndDeletedFalse(payload.getTypeId()).
                orElseThrow(() -> new PetClinicException("This types id is not exists , please insert an other id!"));
        pets.setName(payload.getName());
        pets.setOwner(user);
        pets.setTypes(types);
        pets.setBirthDate(payload.getBirthDate());
        petsRepository.save(pets);
        return ApiResponse.builder()
                .id(pets.getId())
                .message("Pets updated successfully!")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .success(true)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public ApiResponse deletePets(Long id) {
        Pets pets = petsRepository.findByIdAndActiveTrueAndDeletedFalse(id)
                .orElseThrow(() -> new PetClinicException("Please Enter valid pet id!"));
        pets.setActive(false);
        pets.setDeleted(true);
        petsRepository.save(pets);
        return ApiResponse.builder()
                .id(pets.getId())
                .message("Pets updated successfully!")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .success(true)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public PetsResponse getAllPets() {
        List<Pets> pets = petsRepository.findByActiveTrueAndDeletedFalse();
        List<PetsDto> petsDtos = pets.stream()
                .map(p -> PetsDto
                        .builder()
                        .id(p.getId())
                        .name(p.getName())
                        .DOB(UtilMethod.convertDateFormat(p.getBirthDate()))
                        .petTypes(TypesDto
                                .builder()
                                .typesId(p.getTypes().getId())
                                .name(p.getTypes().getName())
                                .build())
                        .ownerDto(OwnerDto
                                .builder()
                                .id(p.getOwner().getId())
                                .name(p.getOwner().getName())
                                .email(p.getOwner().getUsername())
                                .mobileNumber(p.getOwner().getMobileNumber())
                                .address(p.getOwner().getAddress())
                                .city(p.getOwner().getCity())
                                .build())
                        .build())
                .toList();
        return PetsResponse
                .builder()
                .pets(petsDtos)
                .build();
    }
}
