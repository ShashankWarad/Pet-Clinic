package com.technoboost.pet_clinic.app.resource;


import com.technoboost.pet_clinic.app.payload.VisitsCreatePayload;
import com.technoboost.pet_clinic.app.repository.VetsRepository;
import com.technoboost.pet_clinic.app.repository.VisitsRepository;
import com.technoboost.pet_clinic.app.service.PetsService;
import com.technoboost.pet_clinic.app.service.VetsService;
import com.technoboost.pet_clinic.app.service.VisitService;
import com.technoboost.pet_clinic.security.UserPrincipal;
import com.technoboost.pet_clinic.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class VisitResource {

    private final VisitService visitService;
    private final PetsService petsService;
    private final VetsService vetsService;
    private final VetsRepository vetsRepository;

    @GetMapping("/Book-Appointment")
    public String appointment(Model model) {
        Map<String, Object> bookingData = new HashMap<>();
        bookingData.put("visit", new VisitsCreatePayload());
        bookingData.put("pets",petsService.getAllPets().getPets() );
        bookingData.put("vets",vetsService.getAllVets().getVets());
        model.addAllAttributes(bookingData);
        return "Book-Appointment";
    }

    @PostMapping("/visit")
    public String createVisit(@ModelAttribute("visit") VisitsCreatePayload payload, @CurrentUser UserPrincipal userPrincipal ,Model model) {
         visitService.createVisit(payload, userPrincipal);
         model.addAttribute("date",payload.getVisitDate().toLocalDate());
         model.addAttribute("time",payload.getVisitDate().toLocalTime());
         model.addAttribute("vetName",vetsRepository.findById(payload.getVetId()).get().getFirstName());
         return "booking-success";
    }


    @GetMapping("/all-visit")
    public String getAllVisits( @CurrentUser UserPrincipal userPrincipal, Model model) {
        model.addAttribute("allVisits", visitService.getAllVisit( userPrincipal));
        return "home";
    }

    @GetMapping("/cancel-visit/{id}")
    public String deleteVisit( @PathVariable Long id ,@CurrentUser UserPrincipal userPrincipal) {
        visitService.deleteVisit(id,userPrincipal) ;
        return "redirect:/api/auth/home";
    }
}
