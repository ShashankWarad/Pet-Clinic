package com.technoboost.pet_clinic.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoboost.pet_clinic.response.ApiResponse;
import com.technoboost.pet_clinic.util.UtilMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(mapper.writeValueAsString(ApiResponse.builder()
                .timestamp(UtilMethod.currentDateTime())
                .id(0L)
                .path(UtilMethod.getPath())
                .message(e.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .success(false)
                .status(HttpStatus.UNAUTHORIZED.name())
                .build()));
    }
}