package com.ApiTeste.apiTest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Map<String, String>>handlerNotFound(PostNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error :", ex.getMessage()));
    }
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<Map<String, String>>handlerExternal(RestClientResponseException ex){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Map.of("error", "Falha na API externa" + ex.getStatusCode()));
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Map<String, String>>handlerTimeout(ResourceAccessException ex){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(Map.of("error", "Api externa indisponível ou lenta"+ex.getMessage()));
    }
}
