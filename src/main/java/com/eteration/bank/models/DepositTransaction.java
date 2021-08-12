package com.eteration.bank.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class DepositTransaction extends Transaction implements Serializable {
    public DepositTransaction(Double amount) {
        this.setAmount(amount);
    }

    public DepositTransaction() { }
}
