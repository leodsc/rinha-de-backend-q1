package com.rinhadebackend.dto;

import java.util.Date;

import com.rinhadebackend.enums.TransacaoTipo;

public record TransacoesExtratoDto(Long valor, TransacaoTipo tipo, String descricao, Date realizadaEm) {}
