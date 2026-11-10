package com.ApiTeste.apiTest.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("Id não encontrado: " + id);
    }

}
