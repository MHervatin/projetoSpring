package com.msilva.cursoSpring.resources;

import com.msilva.cursoSpring.dto.EmailDTO;
import com.msilva.cursoSpring.security.UsuarioSpringSecurity;
import com.msilva.cursoSpring.security.utils.JWTUtil;
import com.msilva.cursoSpring.services.AuthService;
import com.msilva.cursoSpring.services.UsuarioService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável por controlar os métodos de Autorização da API.
 *
 * @author Mateus
 */
@RestController
@RequestMapping("/auth")
public class AuthResource {

    /**
     * Provê uma instãncia de JWTUtil.
     */
    @Autowired
    private JWTUtil jWTUtil;

    /**
     * Provê serviços de Autorização.
     */
    @Autowired
    private AuthService authService;

    /**
     * Revalida o token de acesso a API.
     *
     * @param response O {@code HttpServletResponse}.
     *
     * @return o token atualizado no header.
     */
    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UsuarioSpringSecurity usuarioAutenticado
                = UsuarioService.usuarioAutenticado();

        String token = jWTUtil.generateToken(usuarioAutenticado.getUsername());

        response.addHeader("Authorization", "Bearer " + token);

        return ResponseEntity.noContent().build();
    }

    /**
     * Renova a senha do cliente.
     *
     * @param emailDTO contendo o e-mail cuja nova senha será enviada.
     *
     * @return uma resposta de confimação vazia.
     */
    @PostMapping(value = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
        authService.enviaNovaSenha(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }
}
