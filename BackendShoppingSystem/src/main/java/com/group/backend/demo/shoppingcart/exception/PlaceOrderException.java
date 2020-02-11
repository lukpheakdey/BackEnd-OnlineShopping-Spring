package com.group.backend.demo.shoppingcart.exception;

public class PlaceOrderException extends ShoppingCartEmptyException {

    private String messsage;

    public PlaceOrderException(String message){
        this.messsage = message;
    }

    public String getMesssage(){
        return messsage;
    }
}
