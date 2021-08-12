package com.eteration.bank;

import com.eteration.bank.exceptions.InsufficientBalanceException;
import com.eteration.bank.models.Account;
import com.eteration.bank.models.DepositTransaction;
import com.eteration.bank.models.WithdrawalTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    @Test
    public void testCreateAccountAndSetBalance0() {
        Account account = new Account("Burkay Durdu");
        assertEquals(account.getOwner(), "Burkay Durdu");
        assertEquals(account.getBalance(), 0.0);
    }

    @Test
    public void testDepositIntoBankAccount() {
        Account account = new Account("Frodo Baggins");
        account.deposit(100.0);
        assertEquals(account.getBalance(), 100.0);
    }

    @Test
    public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
        Account account = new Account("Samwise Gamgee");
        account.deposit(100.0);
        assertEquals((double) account.getBalance(), 100);
        account.withdraw(50.0);
        assertEquals((double) account.getBalance(), 50);
    }

    @Test
    public void testWithdrawException() {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            Account account = new Account("Peregrin Took");
            account.deposit(100.0);
            account.withdraw(500.0);
        });
    }

    @Test
    public void testTransactions() throws InsufficientBalanceException {
        // Create account
        Account account = new Account("Boromir");
        assertEquals(account.getDepositTransactions().size(), 0);

        // Deposit Transaction
        DepositTransaction depositTrx = new DepositTransaction(100.0);
        assertNotNull(depositTrx.getCreatedAt());
        account.post(depositTrx);
        assertEquals(account.getBalance(), 100.0);
        assertEquals(account.getDepositTransactions().size(), 1);

        // Withdrawal Transaction
        WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60.0);
        assertNotNull(withdrawalTrx.getCreatedAt());
        account.post(withdrawalTrx);
        assertEquals((double) account.getBalance(), 40.0);
        assertEquals(account.getWithdrawalTransactions().size(), 1);
    }

}
