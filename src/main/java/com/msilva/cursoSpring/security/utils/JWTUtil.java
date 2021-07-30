package com.msilva.cursoSpring.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por provêr utilitarios de segurança.
 *
 * @author Mateus
 */
@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.timeout}")
    private Long jwtTimeout;

    /**
     * Gera um token de autenticação.
     *
     * @param username Nome de usuário (email).
     *
     * @return o token gerado.
     */
    public String generateToken(String username) {
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + jwtTimeout))
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();
    }

    /**
     * Valida o token informado.
     *
     * @param token a ser validado.
     *
     * @return {@code true} se for válido; caso contrário {@code false}.
     */
    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date dataAtual = new Date(System.currentTimeMillis());

            if (username != null
                    && expirationDate != null
                    && dataAtual.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna o username através do token.
     *
     * @param token a ser utilizado para buscar o username.
     *
     * @return o username presente no token; caso contrário {@code null}.
     */
    public String getUsername(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            return claims.getSubject();
        }

        return null;
    }

    /**
     * Retorna as reivindicações através do token.
     *
     * @param token a ser utilizado para buscar as reivindicações.
     *
     * @return as reivindicações; Caso contrário {@code null}.
     */
    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret.getBytes())
                    .parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            return null;
        }
    }
}
