package com.group.backend.demo.payment.service.impl;

import com.group.backend.demo.email.EmailService;
import com.group.backend.demo.payment.model.MasterCard;
import com.group.backend.demo.payment.model.PaymentCard;
import com.group.backend.demo.payment.repository.MasterCardRepository;
import com.group.backend.demo.payment.repository.VisaCardRepository;
import com.group.backend.demo.payment.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {

    public static final String MASTER_CARD = "MasterCard";
    public static final String VISA = "Visa";


    @Autowired
    private EmailService emailService;


    @Autowired
    private MasterCardRepository masterCardRepository;

    @Autowired
    private VisaCardRepository visaCardRepository;

    public PaymentCard findCard(String cardNumber, String cardName, String expireDate, int cardCvv, String cardType) {
        boolean isMasterCard = cardType != null && !cardType.isEmpty() && cardType.toLowerCase().equals(MASTER_CARD);
        boolean isVisaCard = cardType != null && !cardType.isEmpty() && cardType.toLowerCase().equals(VISA);


        System.out.println(cardName+ " + " + cardType + " " + cardNumber);


        PaymentCard card = null;

        card = masterCardRepository.findByCardNumberAndCardNameAndExpireDateAndCardCvv(cardNumber, cardName, expireDate, cardCvv);

        System.out.println(card);


        if (isMasterCard) {
            card = masterCardRepository.findCard(cardNumber, cardName, expireDate, cardCvv);
        } else if (isVisaCard) {
            card = visaCardRepository.findCard(cardNumber, cardName, expireDate, cardCvv);
        }

        return card;
    }


    @Override
    public boolean verifyCard(String cardNumber, String cardName, String expireDate, int cardCvv, String cardType) {

        PaymentCard card = findCard(cardNumber, cardName, expireDate, cardCvv, cardType);
        return card != null;
    }

    @Override
    public boolean checkBalance(String cardNumber, String cardName, String expireDate, int cardCvv, String cardType, Double balance) {
        PaymentCard card = findCard(cardNumber, cardName, expireDate, cardCvv, cardType);

        return card.getBalance() >= balance;
    }

    @Override
    public boolean makePayment(String cardNumber, String cardName, String expireDate, int cardCvv, String cardType, Double amount) {


        //add sample data.
        // samplecardInfo(); //create sample cards.

        PaymentCard card = findCard(cardNumber, cardName, expireDate, cardCvv, cardType);

        if(card == null) return false;


        System.out.println(amount);

        if(!checkBalance(cardNumber, cardName,expireDate,cardCvv,cardType,amount)) return false;

        double substractedBalance = card.getBalance() - amount;

        card.setBalance(substractedBalance);

        boolean isMasterCard = cardType != null && !cardType.isEmpty() && cardType.equalsIgnoreCase(MASTER_CARD);

        boolean isSuccess = false;

        if (isMasterCard) {
            masterCardRepository.save(card); //updating balance.
            isSuccess = true;
        } else{//we have only two card type.. else its visa card.
            visaCardRepository.save(card); //updating balance.
            isSuccess = true;
        }
        //send an email.


        return isSuccess;
    }

    public void samplecardInfo() {
            PaymentCard masterCard1 = new MasterCard("5500001000000001", "James", "2023/12", 123, "active", 7550);
            PaymentCard masterCard2 = new MasterCard("4569123987435678", "Edwin oigo", "2023/12", 345, "active", 1234);
           PaymentCard masterCard3 = new MasterCard("908765432143", "Luk sleepy", "2023/12", 456, "active", 6789);
           PaymentCard masterCard4 = new MasterCard("098756341287", "Joseph jasper", "2023/12", 567, "active", 34566);
           masterCardRepository.save(masterCard1);
        masterCardRepository.save(masterCard1);
        masterCardRepository.save(masterCard2);
        masterCardRepository.save(masterCard3);
        masterCardRepository.save(masterCard4);

    }
}



