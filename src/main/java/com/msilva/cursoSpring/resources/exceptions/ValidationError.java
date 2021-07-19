package com.msilva.cursoSpring.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por encapsular e exibir as exceções de validação e seus
 * erros.
 *
 * @author Mateus
 */
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer statusHttp, String mensagem, Long timeStamp) {
        super(statusHttp, mensagem, timeStamp);
    }

    /**
     * Retorna a lista de erros de validação.
     *
     * @return A lista de erros.
     */
    public List<FieldMessage> getErrors() {
        return errors;
    }

    /**
     * Adiciona um erro de validação a lista de erros.
     *
     * @param fieldName O nome do campo que gerou o erro de validação.
     * @param message A mensagem de erro.
     */
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
