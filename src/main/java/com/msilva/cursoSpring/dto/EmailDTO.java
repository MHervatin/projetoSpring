package com.msilva.cursoSpring.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Classe responsável por encapsular dados de email.
 *
 * @author Mateus
 */
public class EmailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Email(message = "E-mail inválido!")
    private String email;

    public EmailDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
