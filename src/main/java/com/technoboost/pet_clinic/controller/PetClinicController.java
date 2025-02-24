package com.technoboost.pet_clinic.controller;


import com.technoboost.pet_clinic.app.service.VisitService;
import com.technoboost.pet_clinic.security.UserPrincipal;
import com.technoboost.pet_clinic.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class PetClinicController {

    private final VisitService visitService;

    @GetMapping({"/home", "/"})
    public String home(Model model, @CurrentUser UserPrincipal userPrincipal){
        model.addAttribute("visitedDetails",visitService.getAllVisit(userPrincipal).getVisitedList());
        model.addAttribute("now", LocalDateTime.now());
        return "home";
    }
}
