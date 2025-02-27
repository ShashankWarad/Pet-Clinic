package com.technoboost.pet_clinic.controller;

import com.technoboost.pet_clinic.exception.PetClinicException;
import com.technoboost.pet_clinic.payload.CreateUserPayload;
import com.technoboost.pet_clinic.repository.UserRepository;
import com.technoboost.pet_clinic.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new CreateUserPayload());
        return "signup";
    }


    @PostMapping("/signup")
    public String processSignup(@Valid @ModelAttribute("user") CreateUserPayload userDTO,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "signup";
        }

        try {
            authService.signUp(userDTO);
            redirectAttributes.addFlashAttribute("message", "Signup successful! Please login.");
            return "redirect:/login";
        } catch (PetClinicException e) {
            result.rejectValue("username", "error.user", e.getMessage()); // Show the actual error message
            return "signup";
        } catch (Exception e) {
            result.rejectValue("username", "error.user", "An account already exists for this username.");
            return "signup";
        }
    }
}
