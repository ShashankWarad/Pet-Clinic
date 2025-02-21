package com.technoboost.pet_clinic.app.resource;

import com.technoboost.pet_clinic.app.response.VetsResponse;
import com.technoboost.pet_clinic.app.service.VetsService;
import com.technoboost.pet_clinic.app.payload.VetCreatePayload;
import com.technoboost.pet_clinic.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VetsResource {

    private final VetsService vetsService;


    @PostMapping("/add-vet")
    public ResponseEntity<ApiResponse> createVet(@RequestBody VetCreatePayload vetCreatePayload){
        return ResponseEntity.ok(vetsService.createVet(vetCreatePayload));
    }

    @GetMapping("/getAllVets")
    public ResponseEntity<VetsResponse> getAllVets(){
        return ResponseEntity.ok(vetsService.getAllVets());
    }
}
