package com.group.backend.demo.shoppingcart.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileUploadException extends Exception {

    public FileUploadException(){

    }
}
