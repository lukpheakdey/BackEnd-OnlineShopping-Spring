package com.group.backend.demo.payment.repository;

import com.group.backend.demo.payment.model.PaymentCard;
import com.group.backend.demo.payment.model.VisaCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaCardRepository extends JpaRepository<PaymentCard, Long> {

    @Query("select c from VisaCard c where c.cardNumber=?1 and c.cardName =?2 and c.expireDate = ?3 and c.cardCvv = ?4")
    public VisaCard findCard(String cardNumber, String cardName, String expireDate, int cardCvv);
}