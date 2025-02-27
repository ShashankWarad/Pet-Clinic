package com.technoboost.pet_clinic.util;

import com.technoboost.pet_clinic.exception.PetClinicException;
import com.technoboost.pet_clinic.model.User;
import com.technoboost.pet_clinic.security.UserPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
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

    public static String convertDateFormat(LocalDate date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return date.format(formatter);
    }

    public static LocalDateTime convertStringToDateFormat(String date) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    public User checkPrincipal(UserPrincipal principal) {
        if (principal == null || principal.getUser() == null) {
            throw new PetClinicException("Unauthorized Data");
        }
        return principal.getUser();
    }

}
