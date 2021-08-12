package com.eteration.bank.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(Double amount) {
        this.setAmount(amount);
    }

    public WithdrawalTransaction() { }
}
