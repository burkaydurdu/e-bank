package com.eteration.bank.dto;

import com.eteration.bank.models.Operator;
import com.eteration.bank.models.PhoneBillPaymentTransaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PhoneBillPaymentTransactionDTO extends TransactionDTO {

    private String type = PhoneBillPaymentTransaction.class.getSimpleName();
    private Operator operator;
    private String phoneNumber;

    public PhoneBillPaymentTransactionDTO(UUID id,
                                          Double amount,
                                          LocalDateTime createdAt,
                                          Operator operator,
                                          String phoneNumber) {
        setId(id);
        setAmount(amount);
        setCreatedAt(createdAt);
        setOperator(operator);
        setPhoneNumber(phoneNumber);
    }

    public PhoneBillPaymentTransactionDTO() {}
}
