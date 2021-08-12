package com.eteration.bank.services;

import com.eteration.bank.models.Account;
import com.eteration.bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String owner) {
        Account account = new Account(owner);
        return accountRepository.save(account);
    }

    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
