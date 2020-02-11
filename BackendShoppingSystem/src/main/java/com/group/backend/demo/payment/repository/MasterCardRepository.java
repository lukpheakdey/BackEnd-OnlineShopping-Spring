package com.group.backend.demo.payment.repository;

import com.group.backend.demo.payment.model.MasterCard;
import com.group.backend.demo.payment.model.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterCardRepository extends JpaRepository<PaymentCard, Long> {


    @Query("select c from MasterCard c where c.cardNumber=?1 and c.cardName =?2 and c.expireDate = ?3 and c.cardCvv = ?4")
    public MasterCard findCard(String cardNumber, String cardName, String expireDate, int cardCvv);



    PaymentCard findByCardNumberAndCardNameAndExpireDateAndCardCvv(String cardNumber, String cardName, String expireDate, int cardCvv);




}