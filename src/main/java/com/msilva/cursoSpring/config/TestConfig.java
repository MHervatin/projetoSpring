package com.msilva.cursoSpring.config;

import com.msilva.cursoSpring.services.DBService;
import com.msilva.cursoSpring.services.EmailService;
import com.msilva.cursoSpring.services.MockEmailService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configurações para o perfil de teste.
 *
 * @author Mateus
 */
@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    /**
     * Efetua a instanciação dos dados de teste do banco de dados de teste;
     *
     * @return True.
     *
     * @throws ParseException
     */
    @Bean
    public boolean instanciacaoBancoDeDados() throws ParseException {
        dbService.instanciarBancoDadosTeste();
        return true;
    }

    /**
     * Retorna uma instância de {@link MockEmailService}.
     *
     * @return uma instância de {@link MockEmailService}.
     */
    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
