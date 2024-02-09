package com.rinhadebackend.mapper;

import org.hibernate.mapping.List;

import com.rinhadebackend.dto.CriarTransacaoDtoInput;
import com.rinhadebackend.dto.TransacoesExtratoDto;
import com.rinhadebackend.entity.TransacoesEntity;

public class TransacaoMapper {
    public static TransacoesEntity toTransacoesEntity(CriarTransacaoDtoInput input) {
        TransacoesEntity transacao =  new TransacoesEntity();
        transacao.setDescricao(input.descricao());
        transacao.setTipo(input.tipo());
        transacao.setValor(input.valor());

        return transacao;
    }

    public static TransacoesExtratoDto toTransacoesExtratoDto(TransacoesEntity transacao) {
        TransacoesExtratoDto dto = new TransacoesExtratoDto(
            transacao.getValor(),
            transacao.getTipo(),
            transacao.getDescricao(),
            transacao.getRealizadaEm()
        );
        return dto;
    }
}
