package com.group.backend.demo.shoppingcart.exception;

public class ProductNotFoundException extends Exception {

    private String messsage;

    public ProductNotFoundException() {

    }

    public ProductNotFoundException(String message){
        this.messsage = message;
    }

    public String getMesssage(){
        return messsage;
    }
}
