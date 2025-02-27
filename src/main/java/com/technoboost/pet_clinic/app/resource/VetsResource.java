package com.technoboost.pet_clinic.app.resource;


import com.technoboost.pet_clinic.app.response.VetsResponse;
import com.technoboost.pet_clinic.app.service.VetsService;
import com.technoboost.pet_clinic.app.payload.VetCreatePayload;
import com.technoboost.pet_clinic.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VetsResource {

    private final VetsService vetsService;


    @PostMapping("/add-vet")
    public ResponseEntity<ApiResponse> createVet(@RequestBody VetCreatePayload vetCreatePayload){
        return ResponseEntity.ok(vetsService.createVet(vetCreatePayload));
    }

//    @GetMapping("/bookingAppointments-form")
//    public VetsResponse getAllVets(){
//        return vetsService.getAllVets();
//    }
//
//    @GetMapping("/getAllVets")
//    public VetsResponse getAllVets(@ModelAttribute("") AppointBooingPayload appointBooingPayload){
//        return vetsService.getAllVets();
//    }
}
