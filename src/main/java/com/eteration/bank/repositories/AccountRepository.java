package com.eteration.bank.repositories;

import com.eteration.bank.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
    Account findByAccountNumber(String accountNumber);
}
