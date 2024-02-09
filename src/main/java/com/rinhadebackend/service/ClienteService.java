package com.rinhadebackend.service;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rinhadebackend.dto.CriarTransacaoDtoInput;
import com.rinhadebackend.dto.CriarTransacaoDtoOutput;
import com.rinhadebackend.dto.Extrato;
import com.rinhadebackend.dto.Saldo;
import com.rinhadebackend.dto.TransacoesExtratoDto;
import com.rinhadebackend.entity.ClienteEntity;
import com.rinhadebackend.entity.TransacoesEntity;
import com.rinhadebackend.enums.TransacaoTipo;
import com.rinhadebackend.errors.ApiErrors;
import com.rinhadebackend.errors.ClienteLimiteMaximoAtingidoException;
import com.rinhadebackend.errors.ResourceDoesNotExistException;
import com.rinhadebackend.mapper.TransacaoMapper;
import com.rinhadebackend.repository.ClienteRepository;
import com.rinhadebackend.repository.TransacaoRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    private CriarTransacaoDtoOutput creditarSaldo(ClienteEntity cliente, CriarTransacaoDtoInput transacao) throws ClienteLimiteMaximoAtingidoException {
        Long saldo = cliente.getSaldo() - transacao.valor();
        if (saldo < cliente.getLimite()*-1) {
            throw new ClienteLimiteMaximoAtingidoException();
        } else {
            cliente.setSaldo(saldo);
            clienteRepository.save(cliente);
            return new CriarTransacaoDtoOutput(cliente.getLimite(), saldo);
        }
    }

    private CriarTransacaoDtoOutput debitarSaldo(ClienteEntity cliente, CriarTransacaoDtoInput transacao) {
        cliente.setSaldo(cliente.getSaldo() + transacao.valor());
        clienteRepository.save(cliente);
        return new CriarTransacaoDtoOutput(cliente.getLimite(), cliente.getSaldo());
    }
    
    public boolean checkIfUserExists() {
        Optional<ClienteEntity> cliente = clienteRepository.findById(1L);
        return cliente.isPresent();
    }

    @Transactional
    public CriarTransacaoDtoOutput realizarTransacao(CriarTransacaoDtoInput transacao, Long id) throws ResourceDoesNotExistException, ClienteLimiteMaximoAtingidoException {
        ClienteEntity cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceDoesNotExistException(ApiErrors.CLIENTE_DOES_NOT_EXIST.code));

        CriarTransacaoDtoOutput dto;
        if (transacao.tipo() == TransacaoTipo.c) {
            dto = creditarSaldo(cliente, transacao);
        } else {
            dto = debitarSaldo(cliente, transacao);
        }

        TransacoesEntity transacaoEntity = TransacaoMapper.toTransacoesEntity(transacao);
        transacaoEntity.setCliente(cliente);
        transacaoRepository.save(transacaoEntity);
        return dto;
    }

    public Extrato coletarUltimosExtratos(Long id) throws ResourceDoesNotExistException {
        ClienteEntity cliente = clienteRepository.findClienteById(id)
            .orElseThrow(() -> new ResourceDoesNotExistException(""));
        Saldo saldo = new Saldo(cliente.getSaldo(), cliente.getLimite());

        List<TransacoesExtratoDto> transacoesExtrato = transacaoRepository.findLast10Transacoes(id)
            .get()
            .stream()
            .map(transacao -> {
                return TransacaoMapper.toTransacoesExtratoDto(transacao);
            }).toList();

        return new Extrato(saldo, transacoesExtrato);
    }
}
