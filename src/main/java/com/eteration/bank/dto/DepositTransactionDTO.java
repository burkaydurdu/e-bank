package com.eteration.bank.dto;

import com.eteration.bank.models.DepositTransaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DepositTransactionDTO extends TransactionDTO {

    private String type = DepositTransaction.class.getSimpleName();

    public DepositTransactionDTO(UUID id, Double amount, LocalDateTime createdAt) {
        setId(id);
        setAmount(amount);
        setCreatedAt(createdAt);
    }

    public DepositTransactionDTO() {}
}
