package com.eteration.bank.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionDTO {
    private UUID id;
    private Double amount;
    private LocalDateTime createdAt;
}
