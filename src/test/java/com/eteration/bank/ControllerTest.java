package com.eteration.bank;

import com.eteration.bank.controllers.AccountController;
import com.eteration.bank.exceptions.InsufficientBalanceException;
import com.eteration.bank.models.Account;
import com.eteration.bank.models.DepositTransaction;
import com.eteration.bank.models.WithdrawalTransaction;
import com.eteration.bank.requests.CreateTransactionRequest;
import com.eteration.bank.responses.CreateTransactionResponse;
import com.eteration.bank.responses.ShowAccountResponse;
import com.eteration.bank.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class ControllerTest {

    @Spy
    @InjectMocks
    private AccountController controller;

    @Mock
    private AccountService service;

    @Test
    public void givenId_Credit_thenReturnJson() throws Exception {
        Account account = new Account("Burkay Durdu");

        CreateTransactionRequest request1 = new CreateTransactionRequest();
        request1.setAmount(1000.0);

        doReturn(account).when(service).findByAccountNumber(account.getAccountNumber());

        ResponseEntity<CreateTransactionResponse> result = controller.credit( account.getAccountNumber(), request1);
        verify(service, times(1)).findByAccountNumber(account.getAccountNumber());
        assertEquals("OK", result.getBody().getStatus());
    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson() throws Exception {
        Account account = new Account("Burkay Durdu");

        CreateTransactionRequest request1 = new CreateTransactionRequest();
        request1.setAmount(1000.0);

        CreateTransactionRequest request2 = new CreateTransactionRequest();
        request2.setAmount(50.0);

        doReturn(account).when(service).findByAccountNumber(account.getAccountNumber());
        ResponseEntity<CreateTransactionResponse> result = controller.credit( account.getAccountNumber(), request1);
        ResponseEntity<CreateTransactionResponse> result2 = controller.debit( account.getAccountNumber(), request2);

        verify(service, times(2)).findByAccountNumber(account.getAccountNumber());
        assertEquals("OK", result.getBody().getStatus());
        assertEquals("OK", result2.getBody().getStatus());
        assertEquals(950.0, account.getBalance(),0.001);
    }

    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson() throws Exception {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            Account account = new Account("Burkay Durdu");

            CreateTransactionRequest request1 = new CreateTransactionRequest();
            request1.setAmount(1000.0);

            CreateTransactionRequest request2 = new CreateTransactionRequest();
            request2.setAmount(5000.0);

            doReturn(account).when(service).findByAccountNumber(account.getAccountNumber());

            ResponseEntity<CreateTransactionResponse> result = controller.credit( account.getAccountNumber(), request1);
            assertEquals("OK", result.getBody().getStatus());
            assertEquals(1000.0, account.getBalance(),0.001);
            verify(service, times(1)).findByAccountNumber(account.getAccountNumber());

            ResponseEntity<CreateTransactionResponse> result2 = controller.debit( account.getAccountNumber(), request2);
        });
    }

    @Test
    public void givenId_GetAccount_thenReturnJson() throws Exception {
        Account account = new Account("Burkay Durdu");

        doReturn(account).when(service).findByAccountNumber(account.getAccountNumber());
        ResponseEntity<ShowAccountResponse> result = controller.show( account.getAccountNumber());
        verify(service, times(1)).findByAccountNumber(account.getAccountNumber());
        assertEquals(new ShowAccountResponse(account), result.getBody());
    }

}
