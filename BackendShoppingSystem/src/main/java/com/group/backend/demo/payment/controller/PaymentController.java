package com.group.backend.demo.payment.controller;

import com.group.backend.demo.authentication.model.Success;
import com.group.backend.demo.payment.model.PaymentRequest;
import com.group.backend.demo.payment.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PaymentController {

    @Autowired
    private IPaymentService paymentServiceImpl;

    @PostMapping(value = "/makePayment")
    public ResponseEntity<Success> makePayment(@RequestBody PaymentRequest paymentForm) {
        if(paymentServiceImpl.makePayment(paymentForm.getCardNo(), paymentForm.getCardName(),
                paymentForm.getExpirationDate(), paymentForm.getCcv(), paymentForm.getCardType(), paymentForm.getAmount()))
            return ResponseEntity.ok(new Success("Successful paid"));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Success("Failed to make payment!"));

    }

}