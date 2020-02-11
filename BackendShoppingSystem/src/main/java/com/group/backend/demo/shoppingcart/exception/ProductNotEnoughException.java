package com.group.backend.demo.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Quantity not enough")
public class ProductNotEnoughException extends Exception {

    private String messsage;

    public ProductNotEnoughException() {
    }

    public ProductNotEnoughException(String message){
        this.messsage = message;
    }

    public String getMesssage(){
        return messsage;
    }
}
