package com.rinhadebackend.dto;

import com.rinhadebackend.enums.TransacaoTipo;

public record CriarTransacaoDtoInput (String descricao, TransacaoTipo tipo, Long valor) {};
