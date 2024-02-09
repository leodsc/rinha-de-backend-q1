package com.rinhadebackend.errors;

public enum ApiErrors {
    CLIENTE_LIMITE_MAXIMO_ATINGIDO("400000"),
    CLIENTE_DOES_NOT_EXIST("404000");


    public String code;

    private ApiErrors(String code) {
        this.code = code;
    }
}
