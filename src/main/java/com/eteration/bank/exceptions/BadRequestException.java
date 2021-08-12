package com.eteration.bank.exceptions;

import java.io.Serial;

public class BadRequestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6338728573504497502L;

    public BadRequestException() {
        super("Request is malformed.");
    }

    public BadRequestException(String message) {
        super("Request is malformed: " + message);
    }
}
