package com.msilva.cursoSpring.config;

import com.msilva.cursoSpring.security.JWTAuthenticationFilter;
import com.msilva.cursoSpring.security.JWTAuthorizationFilter;
import com.msilva.cursoSpring.security.utils.JWTUtil;
import com.msilva.cursoSpring.services.UsuarioDetalhesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Classe responsável por efetuar configurações de segurança da aplicação.
 *
 * @author Mateus
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Provê os serviços de {@code UserDetailsService}.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Instância da classe utilitária de segurança JWT.
     */
    @Autowired
    private JWTUtil jWTUtil;

    private static final String[] PUBLIC_MATCHERS = {};

    private static final String[] PUBLIC_MATCHERS_GET = {
        "/produtos/**",
        "/categorias/**"
    };
    
    private static final String[] PUBLIC_MATCHERS_POST = {
        "/categorias/**"
    };

    /**
     * @inheritDoc
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authentication)
            throws Exception {
        authentication.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable();

        httpSecurity.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilter(
                new JWTAuthenticationFilter(authenticationManager(), jWTUtil));

        httpSecurity.addFilter(
                new JWTAuthorizationFilter(authenticationManager(), jWTUtil,
                        userDetailsService));

        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Método responsavél por configurar o {@code Cors}.
     *
     * @return O {@code CorsConfigurationSource} configurado.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource ubccs
                = new UrlBasedCorsConfigurationSource();

        ubccs.registerCorsConfiguration("/**",
                new CorsConfiguration().applyPermitDefaultValues()
        );

        return ubccs;
    }

    /**
     * Provê uma instância de {@code BCryptPasswordEncoder}.
     *
     * @return o {@code BCryptPasswordEncoder} instânciado.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
