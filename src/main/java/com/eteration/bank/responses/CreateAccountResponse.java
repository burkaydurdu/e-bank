package com.eteration.bank.responses;

import com.eteration.bank.models.Account;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAccountResponse {
    private String owner;
    private String accountNumber;
    private LocalDateTime createdAt;

    public CreateAccountResponse(Account account) {
        this.owner = account.getOwner();
        this.accountNumber = account.getAccountNumber();
        this.createdAt = account.getCreatedAt();
    }
}
