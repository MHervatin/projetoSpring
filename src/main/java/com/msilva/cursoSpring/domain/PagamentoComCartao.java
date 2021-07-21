package com.msilva.cursoSpring.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.msilva.cursoSpring.domain.enums.EstadoPagamento;
import javax.persistence.Entity;

/**
 * Classe filha de {@link Pagamento} responsável por pagamentos com cartão.
 *
 * @author Mateus
 */
@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

    private static final long serialVersionUID = 1L;

    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Long id, Integer numeroDeParcelas,
            EstadoPagamento estado, Pedido pedido) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
