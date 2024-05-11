package com.fusm.servicebroker.servicebroker.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
@Setter
public class GlobalCustomException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public GlobalCustomException(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }
}
