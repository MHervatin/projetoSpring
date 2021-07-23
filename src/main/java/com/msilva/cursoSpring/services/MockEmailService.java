/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msilva.cursoSpring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author Mateus
 */
public class MockEmailService extends AbstractEmailService {

    /**
     * Logger para {@code MockEmailService}.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(MockEmailService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void enviarEmail(SimpleMailMessage message) {
        LOGGER.info("Simulando e-mail...");
        LOGGER.info(message.toString());
        LOGGER.info("E-mail enviado!");
    }

}
