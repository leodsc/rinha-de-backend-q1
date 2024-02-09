package com.rinhadebackend.errors;

public class ClienteLimiteMaximoAtingidoException extends Exception {
    public ClienteLimiteMaximoAtingidoException() {
        super(String.valueOf(ApiErrors.CLIENTE_LIMITE_MAXIMO_ATINGIDO.code));
    }
}
