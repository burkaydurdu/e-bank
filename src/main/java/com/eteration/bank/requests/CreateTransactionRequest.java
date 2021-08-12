package com.eteration.bank.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateTransactionRequest {
    private Double amount;
}


