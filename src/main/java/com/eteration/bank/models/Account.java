package com.eteration.bank.models;

import com.eteration.bank.exceptions.InsufficientBalanceException;
import com.eteration.bank.helpers.GenerateAccountNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table
public class Account {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String owner;

    @Column(unique = true)
    private String accountNumber = GenerateAccountNumber.getSecureRandom();

    @Column
    private Double balance = 0.0;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnore
    private List<DepositTransaction> depositTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnore
    private List<WithdrawalTransaction> withdrawalTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnore
    private List<PhoneBillPaymentTransaction> phoneBillPaymentTransactions = new ArrayList<>();

    @Column(columnDefinition = "timestamp with time zone not null")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Account(String owner) {
        this.owner = owner;
    }

    public Account() {}

    public void post(Transaction transaction) {
        transaction.setAccount(this);

        if (transaction instanceof DepositTransaction) {
            this.depositTransactions.add((DepositTransaction) transaction);
            this.setBalance(this.balance + transaction.getAmount());
            return;
        }

        if (this.balance < transaction.getAmount()) {
            throw new InsufficientBalanceException();
        }

        if (transaction instanceof WithdrawalTransaction) {
            this.withdrawalTransactions.add((WithdrawalTransaction) transaction);
        } else if (transaction instanceof PhoneBillPaymentTransaction) {
            this.phoneBillPaymentTransactions.add((PhoneBillPaymentTransaction) transaction);
        }

        this.setBalance(this.balance - transaction.getAmount());
    }

    public void deposit(Double amount) {
        DepositTransaction transaction = new DepositTransaction(amount);
        this.depositTransactions.add(transaction);
        this.setBalance(this.balance + transaction.getAmount());
    }

    public void withdraw(Double amount) {
        if (this.balance < amount) {
            throw new InsufficientBalanceException();
        }

        WithdrawalTransaction transaction = new WithdrawalTransaction(amount);
        this.withdrawalTransactions.add(transaction);
        this.setBalance(this.balance - transaction.getAmount());
    }
}
