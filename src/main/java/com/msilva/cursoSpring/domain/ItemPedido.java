package com.msilva.cursoSpring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Represeta um {@code ItemPedido}.
 *
 * @author Mateus
 */
@Entity
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @JsonIgnore
    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;

    private Integer quantidade;

    private Double preco;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto,
            Integer quantidade, Double preco) {
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
        id.setPedido(pedido);
        id.setProduto(produto);
    }

    public Double getSubTotal() {
        return (preco - desconto) * quantidade;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final ItemPedido other = (ItemPedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(
                new Locale("pt", "BR"));

        StringBuilder sb = new StringBuilder();
        sb.append("Nome Produto: ");
        sb.append(this.getProduto().getNome());
        sb.append(", Qtde: ");
        sb.append(this.getQuantidade());
        sb.append(", Preço unitário: ");
        sb.append(nf.format(this.getPreco()));
        sb.append(", Sub total: ");
        sb.append(nf.format(this.getSubTotal()));
        sb.append("\n");

        return sb.toString();
    }
}
