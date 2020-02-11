package com.group.backend.demo.payment.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "master_card")
@NoArgsConstructor
public class MasterCard extends PaymentCard {
    public MasterCard(String cardNumber, String cardName, String expireDate,int cardCvv, String status, double balance) {
        super(cardNumber, cardName, expireDate, cardCvv, status, balance);
    }
}

