package com.technoboost.pet_clinic.exception;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> getExceptionDetails(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<String, String>();

        exception.getBindingResult().getAllErrors().forEach(erd -> {
            String errorField = ((FieldError) erd).getField();
            String errorMessage = erd.getDefaultMessage();
            errorMap.put(errorField, errorMessage);
        });
        return errorMap;
    }
}