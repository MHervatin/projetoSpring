package com.msilva.cursoSpring.config;

import com.msilva.cursoSpring.services.DBService;
import com.msilva.cursoSpring.services.EmailService;
import com.msilva.cursoSpring.services.SmtpEmailService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configurações para o perfil de teste.
 *
 * @author Mateus
 */
@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    /**
     * Efetua a instanciação dos dados de teste;
     *
     * @return True se os dados foram instânciados; caso contrário false;
     *
     * @throws ParseException
     */
    @Bean
    public boolean instanciacaoBancoDeDados() throws ParseException {
        if (!"create".equals(strategy)) {
            return false;
        }

        dbService.instanciarBancoDadosTeste();
        return true;
    }

    /**
     * Retorna uma instância de {@link SmtpEmailService}.
     *
     * @return uma instância de {@link SmtpEmailService}.
     */
    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
