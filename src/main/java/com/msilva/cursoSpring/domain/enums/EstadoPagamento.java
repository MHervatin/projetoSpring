package com.msilva.cursoSpring.domain.enums;

/**
 *
 * @author Mateus
 */
public enum EstadoPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int codigo;
    private String descricao;

    private EstadoPagamento(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (EstadoPagamento value : EstadoPagamento.values()) {
            if (codigo.equals(value.getCodigo())) {
                return value;
            }
        }

        throw new IllegalArgumentException("ID de código '" + codigo + "' é"
                + " Inválido");
    }
}
