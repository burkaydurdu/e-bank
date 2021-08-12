package com.eteration.bank.exceptions;

import java.io.Serial;

public class InsufficientBalanceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6338728573504497502L;

    public InsufficientBalanceException() {
        super("Insufficient Balance");
    }

    public InsufficientBalanceException(String message) {
        super("Insufficient Balance: " + message);
    }
}
