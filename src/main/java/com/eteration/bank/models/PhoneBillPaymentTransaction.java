package com.eteration.bank.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PhoneBillPaymentTransaction extends Transaction implements Serializable {
    @Column(nullable = false)
    private Operator operator;

    @Column(nullable = false)
    private String phoneNumber;

    public PhoneBillPaymentTransaction(Operator operator, String phoneNumber, Double amount) {
        setOperator(operator);
        setPhoneNumber(phoneNumber);
        setAmount(amount);
    }

    public PhoneBillPaymentTransaction() { }
}
