package com.maneger.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SecretaryInstitutionException extends RuntimeException{
    public SecretaryInstitutionException(String message){
        super(message);
    }
}
