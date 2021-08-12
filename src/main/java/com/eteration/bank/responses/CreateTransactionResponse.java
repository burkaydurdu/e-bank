package com.eteration.bank.responses;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTransactionResponse {
    private String status = "OK";
    private UUID approvalCode;

    public CreateTransactionResponse(String status, UUID approvalCode) {
        setStatus(status);
        setApprovalCode(approvalCode);
    }

    public CreateTransactionResponse(String status) {
        setStatus(status);
        setApprovalCode(approvalCode);
    }

    public CreateTransactionResponse() { }
}
