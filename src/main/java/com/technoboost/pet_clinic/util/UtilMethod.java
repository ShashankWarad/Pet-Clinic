package com.technoboost.pet_clinic.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

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


}
