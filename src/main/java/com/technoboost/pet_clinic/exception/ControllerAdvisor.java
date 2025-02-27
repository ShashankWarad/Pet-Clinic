package com.technoboost.pet_clinic.exception;

import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.util.UtilMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<?> unauthorizedAccess() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Access Denied");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PetClinicException.class)
    @ResponseBody
    public ResponseEntity<?> generalException(PetClinicException e) {
        return ResponseEntity.ok(ApiResponse.builder()
                .message(e.getMessage())
                .path(UtilMethod.getPath())
                .status(HttpStatus.BAD_REQUEST.name())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .id(0L)
                .success(false)
                .timestamp(UtilMethod.currentDateTime())
                .build());
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.FORBIDDEN.name());
        body.put("statusCode", HttpStatus.FORBIDDEN.value());
        body.put("message", "You do not have the necessary authority to access this resource.");
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
       // return new ResponseEntity<>("You do not have the necessary authority to access this resource.", HttpStatus.FORBIDDEN);
    }
}