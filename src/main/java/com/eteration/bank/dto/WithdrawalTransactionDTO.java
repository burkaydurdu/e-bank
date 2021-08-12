package com.eteration.bank.dto;

import com.eteration.bank.models.WithdrawalTransaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class WithdrawalTransactionDTO extends TransactionDTO {

    private String type = WithdrawalTransaction.class.getSimpleName();

    public WithdrawalTransactionDTO(UUID id, Double amount, LocalDateTime createdAt) {
        setId(id);
        setAmount(amount);
        setCreatedAt(createdAt);
    }

    public WithdrawalTransactionDTO() {}
}
