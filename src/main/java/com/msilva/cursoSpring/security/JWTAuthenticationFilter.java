package com.msilva.cursoSpring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msilva.cursoSpring.dto.CredenciaisDTO;
import com.msilva.cursoSpring.security.utils.JWTUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Provê o filtro de autenticação JWT.
 *
 * @author Mateus
 */
public class JWTAuthenticationFilter
        extends UsernamePasswordAuthenticationFilter {

    /**
     * Provê uma instância de {@code AuthenticationManager}.
     */
    private AuthenticationManager authenticationManager;

    /**
     * Provê uma instância do utilitário de JWT.
     */
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
            JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
            HttpServletResponse resp) throws AuthenticationException {
        try {
            CredenciaisDTO creds = new ObjectMapper()
                    .readValue(req.getInputStream(), CredenciaisDTO.class);

            UsernamePasswordAuthenticationToken authToken
                    = new UsernamePasswordAuthenticationToken(creds.getEmail(),
                            creds.getSenha(), new ArrayList<>());

            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
            HttpServletResponse res, FilterChain chain, Authentication auth)
            throws IOException, ServletException {
        String username
                = ((UsuarioSpringSecurity) auth.getPrincipal()).getUsername();

        String token = jwtUtil.generateToken(username);

        res.addHeader("Authorization", "Bearer " + token);
    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        /**
         * @inheritDoc
         */
        @Override
        public void onAuthenticationFailure(HttpServletRequest request,
                HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
        }

        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Não autorizado\", "
                    + "\"message\": \"Email ou senha inválidos\", "
                    + "\"path\": \"/login\"}";
        }
    }
}
