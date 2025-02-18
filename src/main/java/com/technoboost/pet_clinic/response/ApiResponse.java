package com.technoboost.pet_clinic.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse {
    private Long id;
    private String timestamp;
    private String message;
    private String path;
    private String status;
    private int statusCode;
    private boolean success;
}
