package com.msilva.cursoSpring.domain.enums;

/**
 * Enum para os tipos de {@link Cliente}.
 *
 * @author Mateus
 */
public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Fisica"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descricao;

    private TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (TipoCliente value : TipoCliente.values()) {
            if (value.equals(codigo)) {
                return value;
            }
        }

        throw new IllegalArgumentException("ID de código '" + codigo + "' é"
                + " Inválido");
    }
}
