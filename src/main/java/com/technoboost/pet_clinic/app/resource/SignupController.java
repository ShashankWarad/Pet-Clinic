package com.technoboost.pet_clinic.app.resource;

import com.technoboost.pet_clinic.app.response.OwnerResponse;
import com.technoboost.pet_clinic.app.service.OwnerService;
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

    private final OwnerService ownerService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("owner", new OwnerResponse());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@Valid @ModelAttribute("owner") OwnerResponse ownerDTO,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "signup";
        }

        try {
            ownerService.registerNewOwner(ownerDTO);
            redirectAttributes.addFlashAttribute("message", "Registration successful!");
            return "redirect:/login";
        } catch (Exception e) {
            result.rejectValue("email", "error.user", "An account already exists for this email.");
            return "signup";
        }
    }
}