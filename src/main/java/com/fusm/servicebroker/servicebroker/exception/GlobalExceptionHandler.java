package com.fusm.servicebroker.servicebroker.exception;

import com.fusm.servicebroker.servicebroker.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> exceptionHandled(Exception ex) {
        logger.error("Exception { Service Broker microservice } with details :::: " + ex.getMessage());
        return new ResponseEntity<>(
                new Response<>(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GlobalCustomException.class)
    public ResponseEntity<Response<String>> globalExceptionHandled(GlobalCustomException global) {
        logger.error("Exception { Workflow microservice } with details :::: " + global.getMessage());
        return new ResponseEntity<>(
                new Response<>(global.getStatus(), global.getMessage()),
                global.getStatus());
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<Response<String>> webClientRequestExceptionHandled(Exception ex) {
        logger.error("Exception { Workflow microservice } with details :::: " + ex.getMessage());
        return new ResponseEntity<>(
                new Response<>(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response<String>> httpRequestMethodNotSupportedExceptionHandled(Exception ex) {
        logger.error("Exception { Service Broker microservice } with details :::: " + ex.getMessage());
        return new ResponseEntity<>(
                new Response<>(HttpStatus.METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase()),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response<String>> HttpMessageNotReadableExceptionHandled(Exception ex) {
        logger.error("Exception { Service Broker microservice } with details :::: " + ex.getMessage());
        return new ResponseEntity<>(
                new Response<>(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<String>> methodArgumentNotValidExceptionHandled(
            MethodArgumentNotValidException exception,
            WebRequest webRequest
    ) {
        logger.error("Exception { Service Broker microservice } with details :::: " + exception.getMessage());
        return new ResponseEntity<>(
                new Response<>(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Response<String>> missingRequestHeaderExceptionHanlded() {
        logger.error("Exception { Service Broker microservice } with details :::: " +
                "Missing Request Header Exception -> Token not found at headers");
        return new ResponseEntity<>(
                new Response<>(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase()),
                HttpStatus.UNAUTHORIZED);
    }

}

