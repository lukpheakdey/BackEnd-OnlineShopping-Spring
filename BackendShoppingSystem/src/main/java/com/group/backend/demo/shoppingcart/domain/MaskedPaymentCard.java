package com.group.backend.demo.shoppingcart.domain;

public class MaskedPaymentCard {

    private String maskedCardNumber;

    private String cardType;

    public MaskedPaymentCard(String maskedCardNumber, String cardType) {
        this.maskedCardNumber = maskedCardNumber;
        this.cardType = cardType;
    }

    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    public String getCardType() {
        return cardType;
    }
}
