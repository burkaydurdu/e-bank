package com.eteration.bank.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class Transaction implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(columnDefinition = "timestamp with time zone not null")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
