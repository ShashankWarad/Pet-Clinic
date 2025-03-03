package com.technoboost.pet_clinic.controller;

import com.technoboost.pet_clinic.app.model.Pets;
import com.technoboost.pet_clinic.app.model.Types;
import com.technoboost.pet_clinic.app.payload.PetsCreatePayload;
import com.technoboost.pet_clinic.app.repository.TypesRepository;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("pets/new")
    public String createPet(@Valid @ModelAttribute("petsCreatePayload") PetsCreatePayload payload,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "pets/create";
        }
        ApiResponse response = petsService.createPets(payload, getCurrentUser());

        redirectAttributes.addFlashAttribute("message", "Pet added successfully!");

        return "petAdded";
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
}
