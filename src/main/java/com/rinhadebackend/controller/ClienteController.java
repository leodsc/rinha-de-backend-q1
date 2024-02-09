package com.rinhadebackend.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rinhadebackend.dto.CriarTransacaoDtoInput;
import com.rinhadebackend.dto.CriarTransacaoDtoOutput;
import com.rinhadebackend.dto.Extrato;
import com.rinhadebackend.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    public ClienteService clienteService;

    @Autowired
    private DataSource dataSource;

    @EventListener(ApplicationReadyEvent.class)
    public void loadClientes() {
        if (clienteService.checkIfUserExists() == false) {
            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("data.sql"));
        resourceDatabasePopulator.execute(dataSource);
        }
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<CriarTransacaoDtoOutput> postTransacao(@RequestBody CriarTransacaoDtoInput transacao, @PathVariable Long id) throws Exception {
        CriarTransacaoDtoOutput dto = clienteService.realizarTransacao(transacao, id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<Extrato> getExtratoCliente(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(clienteService.coletarUltimosExtratos(id));
    }
    
    
}
