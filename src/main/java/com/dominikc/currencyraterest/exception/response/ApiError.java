package com.dominikc.currencyraterest.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiError {
    private final LocalDateTime timestamp = LocalDateTime.now();

    private int status;

    private String message;
}
