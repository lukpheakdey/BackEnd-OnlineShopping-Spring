package com.group.backend.demo.payment.service;



public interface IPaymentService {

    public boolean verifyCard(String cardNumber, String cardName, String expireDate, int cardCvv, String cardType);

    public boolean checkBalance(String cardNumber, String cardName, String expireDate, int cardCvv, String cardType, Double balance);

    public boolean makePayment(String cardNumber, String cardName, String expireDate, int cardCvv, String cardType, Double balance);
}

