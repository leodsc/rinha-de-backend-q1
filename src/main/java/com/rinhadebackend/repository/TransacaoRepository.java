package com.rinhadebackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rinhadebackend.dto.TransacoesExtratoDto;
import com.rinhadebackend.entity.TransacoesEntity;
import com.rinhadebackend.queries.TransacaoQueries;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacoesEntity, Long> {
    @Query(value=TransacaoQueries.FIND_LAST_10_TRANSACOES_FROM_CLIENTE, nativeQuery=true)
    Optional<List<TransacoesEntity>> findLast10Transacoes(Long id);
}
