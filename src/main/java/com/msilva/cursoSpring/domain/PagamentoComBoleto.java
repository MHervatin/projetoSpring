/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msilva.cursoSpring.domain;

import com.msilva.cursoSpring.domain.enums.EstadoPagamento;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author Mateus
 */
@Entity
public class PagamentoComBoleto extends Pagamento {

    private static final long serialVersionUID = 1L;

    private Date dataVencimento;

    private Date dataPagamento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Long id, Date dataVencimento, Date dataPagamento,
            EstadoPagamento estado, Pedido pedido) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
