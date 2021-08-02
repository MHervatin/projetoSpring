package com.msilva.cursoSpring.services.exceptions;

/**
 * Exceção para tratar falhas de autorização.
 *
 * @author Mateus
 */
public class AuthorizationException extends RuntimeException {

    /**
     * Construtor para a exceção.
     *
     * @param message Mensagem a ser apresentada na Exceção.
     */
    public AuthorizationException(String message) {
        super(message);
    }

    /**
     * Construtor para a exceção.
     *
     * @param message Mensagem a ser apresentada na Exceção.
     * @param cause {@code Throwable} da exceção.
     */
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
