package com.msilva.cursoSpring.resources.exceptions;

import com.msilva.cursoSpring.services.exceptions.AuthorizationException;
import com.msilva.cursoSpring.services.exceptions.DataIntegrityException;
import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    /**
     * Tratamento para falha na integridade dos dados.
     *
     * @param exception {@code DataIntegrityException} que ocorreu.
     * @param httpServletRequest {@code HttpServletRequest} da exceção.
     *
     * @return
     */
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(
            DataIntegrityException exception,
            HttpServletRequest httpServletRequest) {

        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Tratamento para falha na validação dos dados.
     *
     * @param exception {@code MethodArgumentNotValidException} que ocorreu.
     * @param httpServletRequest {@code HttpServletRequest} da exceção.
     *
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            HttpServletRequest httpServletRequest) {
        ValidationError error = new ValidationError(
                HttpStatus.BAD_REQUEST.value(), "Erro de Validação",
                System.currentTimeMillis());

        exception.getBindingResult().getFieldErrors().stream().forEach(
                erro -> {
                    error.addError(erro.getField(), erro.getDefaultMessage());
                });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Tratamento para exceção de autorização.
     *
     * @param exception {@code AuthorizationException} que ocorreu.
     * @param httpServletRequest {@code HttpServletRequest} da exceção.
     *
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorizationException(
            AuthorizationException exception,
            HttpServletRequest httpServletRequest) {

        StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(),
                exception.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
