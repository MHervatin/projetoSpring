package com.msilva.cursoSpring.services.exceptions;

/**
 * Exceção para tratar falhas na integridade dos dados.
 *
 * @author Mateus
 */
public class DataIntegrityException extends RuntimeException {

    /**
     * Construtor para a exceção.
     *
     * @param message Mensagem a ser apresentada na Exceção.
     */
    public DataIntegrityException(String message) {
        super(message);
    }

    /**
     * Construtor para a exceção.
     *
     * @param message Mensagem a ser apresentada na Exceção.
     * @param cause {@code Throwable} da exceção.
     */
    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}
