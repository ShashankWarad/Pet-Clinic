package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.dto.TypesDto;
import com.technoboost.pet_clinic.app.model.Types;
import com.technoboost.pet_clinic.app.payload.TypeCreatePayload;
import com.technoboost.pet_clinic.app.payload.TypeUpdatePayload;
import com.technoboost.pet_clinic.app.repository.TypesRepository;
import com.technoboost.pet_clinic.app.response.TypesResponse;
import com.technoboost.pet_clinic.exception.PetClinicException;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.util.UtilMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TypesServiceImpl implements TypesService {
    private final TypesRepository typesRepository;

    @Override
    public ApiResponse createTypes(TypeCreatePayload payload) {
        boolean b = typesRepository.existsNameInActiveTrueAndDeletedFalse(payload.getName());
        if (b)
            throw new PetClinicException("Name is already exist, Please try another name!");
        Types types = new Types();

        types.setName(payload.getName());
        typesRepository.saveAndFlush(types);
        return ApiResponse.builder()
                .id(types.getId())
                .message("Type added successfully!")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .success(true)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public ApiResponse updateTypes(TypeUpdatePayload payload) {
        Types types = typesRepository.findByIdAndActiveTrueAndDeletedFalse(payload.getId())
                .orElseThrow(() -> new PetClinicException("Types Id is not exists please try another id!"));
        types.setName(payload.getName());
        typesRepository.save(types);
        return ApiResponse.builder()
                .id(types.getId())
                .message("Type updated successfully!")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .success(true)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public ApiResponse deleteTypes(Long id) {
        Types types = typesRepository.findByIdAndActiveTrueAndDeletedFalse(id)
                .orElseThrow(() -> new PetClinicException("Types Id is not exists please try another id!"));
        types.setDeleted(true);
        types.setActive(false);
        return ApiResponse.builder()
                .id(types.getId())
                .message("Type updated successfully!")
                .path(UtilMethod.getPath())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .success(true)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public TypesResponse getAllTypes() {
        List<TypesDto> typesDtos = typesRepository.findAllAndActiveTrueAndDeletedFalse();
        return TypesResponse
                .builder()
                .petTypes(typesDtos)
                .build();
    }
}
