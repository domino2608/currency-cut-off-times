package com.dominikc.currencyraterest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for all kind of API errors
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
