package com.eteration.bank.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateAccountRequest {
    private String owner;
}
