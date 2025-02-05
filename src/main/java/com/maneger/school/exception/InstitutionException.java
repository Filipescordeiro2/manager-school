package com.maneger.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InstitutionException extends RuntimeException{

    public InstitutionException(String message){
        super(message);
    }
}
