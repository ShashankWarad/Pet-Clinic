package com.technoboost.pet_clinic.app.resource;

import com.technoboost.pet_clinic.app.payload.PetsCreatePayload;
import com.technoboost.pet_clinic.app.repository.TypesRepository;
import com.technoboost.pet_clinic.app.service.PetsService;
import com.technoboost.pet_clinic.repository.UserRepository;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.security.UserPrincipal;
import com.technoboost.pet_clinic.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PetsResource {
    private final PetsService petsService;
    private final TypesRepository petTypeRepository;
    private final UserRepository ownerRepository;

//    @GetMapping("/pets/new")
//    public String showCreatePetForm(Model model) {
//
//        model.addAttribute("petsCreatePayload", new PetsCreatePayload());
//
//
//        model.addAttribute("petTypes", petTypeRepository.findAll());
//
//
//        model.addAttribute("owners", ownerRepository.findAll());
//
//        return "AddPet";
//    }
//
//    @PostMapping("/pets/new")
//    @ResponseBody
//    public ResponseEntity<ApiResponse> createPets(@RequestBody PetsCreatePayload payload,
//                                                  @CurrentUser UserPrincipal userPrincipal) {
//        return ResponseEntity
//                .ok(petsService.createPets(payload, userPrincipal));
//    }
}
