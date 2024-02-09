package com.rinhadebackend.queries;

public abstract class TransacaoQueries {
    public static final String FIND_LAST_10_TRANSACOES_FROM_CLIENTE = """
        SELECT id, valor, tipo, descricao, realizada_em, cliente_id FROM transacoes
        WHERE cliente_id = ?1
        ORDER BY realizada_em DESC
        LIMIT 10
    """;
}
