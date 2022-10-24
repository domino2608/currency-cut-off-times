package com.dominikc.currencyraterest.controller.advice;

import com.dominikc.currencyraterest.exception.ApiException;
import com.dominikc.currencyraterest.exception.CurrencyNotFoundException;
import com.dominikc.currencyraterest.exception.response.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class CountryCutOffControllerAdvice {

    @ExceptionHandler(CurrencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError currencyNotFoundException(CurrencyNotFoundException exception) {
        return new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError generalApiErrorHandler(ApiException apiException) {
        return new ApiError(HttpStatus.BAD_REQUEST, apiException.getMessage());
    }

}
