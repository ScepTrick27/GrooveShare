package com.fontys.s3.grooveshare.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserIdException extends ResponseStatusException {
    public InvalidUserIdException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
