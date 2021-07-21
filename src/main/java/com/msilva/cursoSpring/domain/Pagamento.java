package com.msilva.cursoSpring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.msilva.cursoSpring.domain.enums.EstadoPagamento;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * Classe responsavél por prover a estrutura de um pagamento.
 *
 * @author Mateus
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Integer estado;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    @JsonIgnore
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Long id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado != null ? estado.getCodigo() : null;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCodigo();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pagamento other = (Pagamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
