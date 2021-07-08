package com.msilva.cursoSpring.services.exceptions;

/**
 * Exceção para objetos não encontrados.
 *
 * @author Mateus
 */
public class ObjectNotFoundException extends RuntimeException {

    /**
     * Construtor para a exceção.
     *
     * @param message Mensagem a ser apresentada na Exceção.
     */
    public ObjectNotFoundException(String message) {
        super(message);
    }

    /**
     * Construtor para a exceção.
     *
     * @param message Mensagem a ser apresentada na Exceção.
     * @param cause {@code Throwable} da exceção.
     */
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
