package com.eteration.bank.responses;

import com.eteration.bank.dto.DepositTransactionDTO;
import com.eteration.bank.dto.PhoneBillPaymentTransactionDTO;
import com.eteration.bank.dto.TransactionDTO;
import com.eteration.bank.dto.WithdrawalTransactionDTO;
import com.eteration.bank.models.Account;
import com.eteration.bank.models.Transaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShowAccountResponse {
    private String accountNumber;
    private String owner;
    private Double balance;
    private LocalDateTime createdAt;
    private List<TransactionDTO> transactions = new ArrayList<>();

    public ShowAccountResponse(Account account) {
        setAccountNumber(account.getAccountNumber());
        setOwner(account.getOwner());
        setBalance(account.getBalance());
        setCreatedAt(account.getCreatedAt());

        account.getDepositTransactions().forEach(transaction -> {
            transactions.add(new DepositTransactionDTO(
                    transaction.getId(),
                    transaction.getAmount(),
                    transaction.getCreatedAt())
            );
        });

        account.getWithdrawalTransactions().forEach(transaction -> {
            transactions.add(new WithdrawalTransactionDTO(
                    transaction.getId(),
                    transaction.getAmount(),
                    transaction.getCreatedAt())
            );
        });

        account.getPhoneBillPaymentTransactions().forEach(transaction -> {
            transactions.add(new PhoneBillPaymentTransactionDTO(
                    transaction.getId(),
                    transaction.getAmount(),
                    transaction.getCreatedAt(),
                    transaction.getOperator(),
                    transaction.getPhoneNumber())
            );
        });
    }
}
