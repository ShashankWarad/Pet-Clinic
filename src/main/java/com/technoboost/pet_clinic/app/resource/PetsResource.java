package com.technoboost.pet_clinic.app.resource;

import com.technoboost.pet_clinic.app.payload.PetsCreatePayload;
import com.technoboost.pet_clinic.app.payload.PetsUpdatePayload;
import com.technoboost.pet_clinic.app.response.PetsResponse;
import com.technoboost.pet_clinic.app.service.PetsService;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.security.UserPrincipal;
import com.technoboost.pet_clinic.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PetsResource {
    private final PetsService petsService;

    @GetMapping("/pets/new")
    public String showLoginForm() {
        return "AddPet";
    }

    @PostMapping("pet")
    public ResponseEntity<ApiResponse> createPets(@RequestBody PetsCreatePayload payload,
                                                  @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity
                .ok(petsService.createPets(payload, userPrincipal));

    }

    @PutMapping("pet")
    public ResponseEntity<ApiResponse> updatePets(@RequestBody PetsUpdatePayload payload,
                                                  @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity
                .ok(petsService.updatePets(payload, userPrincipal));
    }

    @DeleteMapping("pet/{id}")
    public ResponseEntity<ApiResponse> deletePets(@PathVariable Long id) {
        return ResponseEntity
                .ok(petsService.deletePets(id));
    }

    @GetMapping("pets")
    public ResponseEntity<PetsResponse> getAllPets() {
        return ResponseEntity
                .ok(petsService.getAllPets());
    }
}
