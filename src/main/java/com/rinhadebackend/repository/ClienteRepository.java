package com.rinhadebackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rinhadebackend.entity.ClienteEntity;
import com.rinhadebackend.queries.ClienteQueries;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    @Query(value=ClienteQueries.FIND_SALDO_BY_CLIENTE_ID, nativeQuery=true)
    Optional<ClienteEntity> findClienteById(Long id);
}
