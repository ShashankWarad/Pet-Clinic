package com.technoboost.pet_clinic.controller;

import com.technoboost.pet_clinic.app.model.Pets;
import com.technoboost.pet_clinic.app.model.Types;
import com.technoboost.pet_clinic.app.payload.PetsCreatePayload;
import com.technoboost.pet_clinic.app.repository.TypesRepository;
import com.technoboost.pet_clinic.app.response.PetsResponse;
import com.technoboost.pet_clinic.app.service.PetsService;
import com.technoboost.pet_clinic.repository.UserRepository;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.security.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class PetsController {
    private final PetsService petsService;
    private final TypesRepository petTypeRepository;
    private final UserRepository ownerRepository;
    private final TypesRepository typesRepository;

    @GetMapping("pets/new")
    public String showCreatePetForm(Model model) {
        model.addAttribute("petsCreatePayload", new PetsCreatePayload());
        model.addAttribute("petTypes", petTypeRepository.findAll());
        model.addAttribute("owners", ownerRepository.findAll());
        return "AddPet";
    }

    @PostMapping("/PetAdded")
    public String createPet(@Valid @ModelAttribute("petsCreatePayload") PetsCreatePayload payload,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("petTypes", petTypeRepository.findAll());
            model.addAttribute("owners", ownerRepository.findAll());
            return "AddPet";
        }

        ApiResponse response = petsService.createPets(payload);

        if (response.isSuccess()) {
            Pets addedPet = petsService.getPetByName(payload.getName());

            if (addedPet != null) {
                model.addAttribute("pet", addedPet);
                model.addAttribute("petTypeName", typesRepository.findById(addedPet.getTypes().getId()).map(Types::getName).orElse("Unknown"));
                model.addAttribute("ownerName", ownerRepository.findById(addedPet.getOwner().getId()).map(user -> user.getName()).orElse("Unknown"));
            }

            return "PetAdded";
        } else {
            model.addAttribute("errorMessage", response.getMessage());
            model.addAttribute("petTypes", petTypeRepository.findAll());
            model.addAttribute("owners", ownerRepository.findAll());
            return "AddPet";
        }
    }

//    @GetMapping("")
//    public String getAllPets(Model model) {
//        PetsResponse petsResponse = petsService.getAllPets();
//        model.addAttribute("pets", petsResponse.getPets());
//        return "pets/list";
//    }

    private UserPrincipal getCurrentUser() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

//    @PostMapping("/PetAdded")
//    public String showPetAddedPage(@RequestParam(name = "petId", required = false) Long petId, Model model) {
//
//        if (petId != null) {
//
//            Optional<Pets> petOptional = petsService.getPetById(petId);
//
//            if (petOptional.isPresent()) {
//                Pets pet = petOptional.get();
//
//                model.addAttribute("pet", pet);
//                model.addAttribute("petTypeName", petTypeRepository.findById(pet.getTypes().getId()).map(Types::getName).orElse("Unknown"));
//                model.addAttribute("ownerName", ownerRepository.findById(pet.getOwner().getId()).map(user -> user.getName()).orElse("Unknown"));
//
//                return "PetAdded";
//            } else {
//                model.addAttribute("errorMessage", "Pet not found.");
//                return "error";
//            }
//        } else {
//            model.addAttribute("errorMessage", "Pet ID is required.");
//            return "error";
//        }
//    }
}
