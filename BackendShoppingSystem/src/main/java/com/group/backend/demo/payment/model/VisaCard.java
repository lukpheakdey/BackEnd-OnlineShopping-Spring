package com.group.backend.demo.payment.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "visa_card")
@NoArgsConstructor
public class VisaCard extends PaymentCard {
    public VisaCard(@NotNull(message = "*Please enter card number") String cardNumber, @NotBlank(message = "*Please enter card name") String cardName, @NotNull(message = "*Please enter expire date") String expireDate, @NotNull(message = "*Please enter CVV code") int cardCvv, @NotNull(message = "*Please enter status") String status, @NotNull(message = "*Please enter balance") double balance) {
        super(cardNumber, cardName, expireDate, cardCvv, status, balance);
    }
}
