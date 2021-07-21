package com.msilva.cursoSpring.config;

import com.msilva.cursoSpring.services.DBService;
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
}
