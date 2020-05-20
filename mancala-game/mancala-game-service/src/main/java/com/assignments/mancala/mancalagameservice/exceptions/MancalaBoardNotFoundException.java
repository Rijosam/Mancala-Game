package com.assignments.mancala.mancalagameservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MancalaBoardNotFoundException extends Exception {
    public MancalaBoardNotFoundException(String message){
        super(message);
    }
}
