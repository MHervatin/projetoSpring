package com.msilva.cursoSpring.security;

import com.msilva.cursoSpring.security.utils.JWTUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Provê um filtro de autorização JWT.
 *
 * @author Mateus
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    /**
     * Provê uma instância do utilitário de JWT.
     */
    private final JWTUtil jWTUtil;

    /**
     * Provê os serviços de {@code UserDetailsService}.
     */
    private final UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
            JWTUtil jWTUtil, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jWTUtil = jWTUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * @@inheritDoc
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String autorizacao = request.getHeader("Authorization");

        if (autorizacao != null && autorizacao.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth
                    = getAuthentication(autorizacao.substring(7));

            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * Retorna a autenticação do usuário.
     *
     * @param token a ser utilizado.
     *
     * @return {@code UsernamePasswordAuthenticationToken}; Caso contrário
     * {@code null}.
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jWTUtil.tokenValido(token)) {
            String username = jWTUtil.getUsername(token);
            UserDetails user = userDetailsService.loadUserByUsername(username);

            return new UsernamePasswordAuthenticationToken(user, null,
                    user.getAuthorities());
        }

        return null;
    }
}
