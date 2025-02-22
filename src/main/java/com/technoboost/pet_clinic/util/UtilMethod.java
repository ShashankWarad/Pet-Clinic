package com.technoboost.pet_clinic.util;

import com.technoboost.pet_clinic.exception.PetClinicException;
import com.technoboost.pet_clinic.model.User;
import com.technoboost.pet_clinic.security.UserPrincipal;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilMethod {

    public static String getPath() {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build().toUri();
        return location.getPath();
    }

    public static String currentDateTime() {
        return LocalDateTime.now().toString();
    }

    public User checkPrincipal(UserPrincipal principal) {
        if (principal == null || principal.getUser() == null) {
            throw new PetClinicException("Unauthorized Data");
        }
        return principal.getUser();
    }
    public static String convertDateFormat(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        LocalDate originalDate = date.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return originalDate.format(formatter);

    }


}
