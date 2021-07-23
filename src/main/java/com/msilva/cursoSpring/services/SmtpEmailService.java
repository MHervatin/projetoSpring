package com.msilva.cursoSpring.services;

import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Provê serviços para e-mail Smtp.
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
     * Instância os dados de parêmtro do {@code JavaMailSender}.
     */
    @Autowired
    private JavaMailSender JavaMailSender;

    /**
     * {@inheritDoc }
     */
    @Override
    public void enviarEmail(SimpleMailMessage message) {
        LOGGER.info("Enviando e-mail...");
        mailSender.send(message);
        LOGGER.info("E-mail enviado!");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void enviarHtmlEmail(MimeMessage message) {
        LOGGER.info("Enviando e-mail HTML...");
        JavaMailSender.send(message);
        LOGGER.info("E-mail enviado!");
    }

}
