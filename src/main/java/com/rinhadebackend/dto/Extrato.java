package com.rinhadebackend.dto;

import java.util.List;

public record Extrato(Saldo saldo, List<TransacoesExtratoDto> ultimasTransacoes) {}
