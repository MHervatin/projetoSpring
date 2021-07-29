package com.msilva.cursoSpring.security.utils;

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

}
