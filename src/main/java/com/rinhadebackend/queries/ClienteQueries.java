package com.rinhadebackend.queries;

public abstract class ClienteQueries {
    public static final String FIND_SALDO_BY_CLIENTE_ID="""
        SELECT id, saldo, limite FROM clientes
        WHERE id = ?1
    """;;
}
