package com.eteration.bank.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {
    private HttpStatus status;
    private String message;
    private String error;

    public ApiError(final HttpStatus status, final String message, String error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }
}