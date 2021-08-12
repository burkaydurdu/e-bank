package com.eteration.bank.controllers;

import com.eteration.bank.models.Account;
import com.eteration.bank.models.DepositTransaction;
import com.eteration.bank.models.PhoneBillPaymentTransaction;
import com.eteration.bank.models.WithdrawalTransaction;
import com.eteration.bank.requests.CreatePhoneBillPaymentRequest;
import com.eteration.bank.requests.CreateTransactionRequest;
import com.eteration.bank.responses.CreateTransactionResponse;
import com.eteration.bank.responses.ShowAccountResponse;
import com.eteration.bank.services.AccountService;
import com.eteration.bank.exceptions.BadRequestException;
import com.eteration.bank.requests.CreateAccountRequest;
import com.eteration.bank.responses.CreateAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/v1")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateAccountResponse> create(@RequestBody CreateAccountRequest request) {
        if (request.getOwner() == null) {
            throw new BadRequestException();
        }

        Account account = accountService.createAccount(request.getOwner());
        return new ResponseEntity<>(new CreateAccountResponse(account), HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<ShowAccountResponse> show(@PathVariable("accountNumber") String accountNumber) {
        Account account = accountService.findByAccountNumber(accountNumber);
        return new ResponseEntity<>(new ShowAccountResponse(account), HttpStatus.OK);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<CreateTransactionResponse> credit(@PathVariable("accountNumber") String accountNumber,
                                                            @RequestBody CreateTransactionRequest request) {
        if (request.getAmount() == null) {
            throw new BadRequestException();
        }

        Account account = accountService.findByAccountNumber(accountNumber);
        account.post(new DepositTransaction(request.getAmount()));
        accountService.save(account);

        return new ResponseEntity<>(new CreateTransactionResponse(), HttpStatus.OK);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<CreateTransactionResponse> debit(@PathVariable("accountNumber") String accountNumber,
                                                           @RequestBody CreateTransactionRequest request) {
        if (request.getAmount() == null) {
            throw new BadRequestException();
        }

        Account account = accountService.findByAccountNumber(accountNumber);
        account.post(new WithdrawalTransaction(request.getAmount()));
        accountService.save(account);

        return new ResponseEntity<>(new CreateTransactionResponse(), HttpStatus.OK);
    }

    @PostMapping("/phoneBillPayment/{accountNumber}")
    public ResponseEntity<CreateTransactionResponse> phoneBillPayment(@PathVariable("accountNumber") String accountNumber,
                                                                      @RequestBody CreatePhoneBillPaymentRequest request) {
        if (request.getOperator() == null || request.getPhoneNumber() == null || request.getAmount() == null) {
            throw new BadRequestException();
        }

        Account account = accountService.findByAccountNumber(accountNumber);
        account.post(new PhoneBillPaymentTransaction(request.getOperator(), request.getPhoneNumber(), request.getAmount()));
        accountService.save(account);

        return new ResponseEntity<>(new CreateTransactionResponse(), HttpStatus.OK);
    }
}
