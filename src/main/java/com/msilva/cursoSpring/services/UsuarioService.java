package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.security.UsuarioSpringSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Provê serviços de usuário.
 *
 * @author Mateus
 */
public class UsuarioService {

    /**
     * Busca o usuário autenticado.
     *
     * @return O usuário autenticado.
     */
    public static UsuarioSpringSecurity usuarioAutenticado() {
        try {
            return (UsuarioSpringSecurity) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } catch (Exception ex) {
            return null;
        }
    }
}
