package com.msilva.cursoSpring.resources.exceptions;

import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Responsável por manipular as exceções do recurso.
 *
 * @author Mateus
 */
@ControllerAdvice
public class ResourceExceptionHendler {

    /**
     * Tratamento para exceção de objeto não encontrado.
     *
     * @param exception {@code ObjectNotFoundException} que ocorreu.
     * @param httpServletRequest {@code HttpServletRequest} da exceção.
     *
     * @return
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(
            ObjectNotFoundException exception,
            HttpServletRequest httpServletRequest) {

        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(),
                exception.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
