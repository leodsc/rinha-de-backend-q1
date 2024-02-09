package com.rinhadebackend.dto;

import java.util.Date;

public class Saldo {
    private Long total;
    private Date dataExtrato = new Date();
    private Long limite;

    public Saldo(Long total, Long limite) {
        this.total = total;
        this.limite = limite;
    }

    public Long getTotal() {
        return total;
    }

    public Date getDataExtrato() {
        return dataExtrato;
    }

    public Long getLimite() {
        return limite;
    }

    public void setDataExtrato(Date dataExtrato) {
        this.dataExtrato = dataExtrato;
    }

    public void setLimite(Long limite) {
        this.limite = limite;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
