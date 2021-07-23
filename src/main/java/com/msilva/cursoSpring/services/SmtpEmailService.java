package com.msilva.cursoSpring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author Mateus
 */
public class SmtpEmailService extends AbstractEmailService {

    /**
     * Logger para {@code SmtpEmailService}.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(SmtpEmailService.class);

    /**
     * Instância os dados de parêmtro do {@code MailSender}.
     */
    @Autowired
    private MailSender mailSender;

    /**
     * {@inheritDoc }
     */
    @Override
    public void enviarEmail(SimpleMailMessage message) {
        LOGGER.info("Enviando e-mail...");
        mailSender.send(message);
        LOGGER.info("E-mail enviado!");
    }

}
