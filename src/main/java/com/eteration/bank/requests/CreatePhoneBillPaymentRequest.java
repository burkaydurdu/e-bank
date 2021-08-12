package com.eteration.bank.requests;

import com.eteration.bank.models.Operator;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreatePhoneBillPaymentRequest {
    private Double amount;
    private Operator operator;
    private String phoneNumber;
}
