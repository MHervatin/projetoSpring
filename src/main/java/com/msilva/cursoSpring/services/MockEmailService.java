package com.msilva.cursoSpring.services;

import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Provê serviços para e-mail de teste.
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
        LOGGER.info("Simulando envio de e-mail...");
        LOGGER.info(message.toString());
        LOGGER.info("E-mail enviado!");
    }

    @Override
    public void enviarHtmlEmail(MimeMessage message) {
        LOGGER.info("Simulando envio de e-mail HTML...");
        LOGGER.info(message.toString());
        LOGGER.info("E-mail enviado!");
    }

}
