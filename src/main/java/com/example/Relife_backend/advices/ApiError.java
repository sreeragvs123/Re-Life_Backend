package com.example.Relife_backend.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiError {
    private String message;
    private HttpStatus status;
}
