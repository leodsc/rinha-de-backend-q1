package com.rinhadebackend.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="test")
public class ResourceDoesNotExistException extends Exception {
    public ResourceDoesNotExistException(String message) {
        super(message);
    }

    public ResourceDoesNotExistException(int message) {
        super(String.valueOf(message));
    }
}
