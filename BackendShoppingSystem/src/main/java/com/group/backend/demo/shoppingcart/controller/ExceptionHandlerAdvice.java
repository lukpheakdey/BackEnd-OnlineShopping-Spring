package com.group.backend.demo.shoppingcart.controller;

import com.group.backend.demo.shoppingcart.exception.PlaceOrderException;
import com.group.backend.demo.shoppingcart.exception.ProductNotEnoughException;
import com.group.backend.demo.shoppingcart.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(FileNotFoundException e){

        ErrorMessage error  = new ErrorMessage("File not found", "404");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlaceOrderException.class)
    public ResponseEntity<ErrorMessage> handleException(PlaceOrderException e){

        ErrorMessage error  = new ErrorMessage("No items in cart", "400");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(ProductNotFoundException e){

        ErrorMessage error  = new ErrorMessage("No product found", "404");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotEnoughException.class)
    public ResponseEntity<ErrorMessage> handleException(ProductNotEnoughException e){

        ErrorMessage error  = new ErrorMessage("No product enough found", "400");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    class ErrorMessage {

        String message;
        String status;

        ErrorMessage(String message, String status){
            this.message = message;
            this.status = status;
        }

        String getMessage(){
            return message;
        }

        String getStatus(){
            return status;
        }
    }

}
