package com.msilva.cursoSpring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msilva.cursoSpring.domain.PagamentoComBoleto;
import com.msilva.cursoSpring.domain.PagamentoComCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Classe utilizada para configuração do Jackson.
 *
 * @author Mateus
 */
@Configuration
public class JacksonConfig {

    /**
     * Registra subtipos no object mapper do Jackson.
     *
     * @return retorna o object mapper configurado.
     *
     * @see
     * https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
     */
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PagamentoComCartao.class);
                objectMapper.registerSubtypes(PagamentoComBoleto.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
