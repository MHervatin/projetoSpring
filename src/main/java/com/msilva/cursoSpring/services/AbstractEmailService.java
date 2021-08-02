package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.domain.Pedido;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Provê seviços abstraidos de e-mail.
 *
 * @author Mateus
 */
public abstract class AbstractEmailService implements EmailService {

    /**
     * Valor do sender presente no application.properties.
     */
    @Value("${default.sender}")
    private String sender;

    /**
     * Injeção para TemplateEngine.
     */
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Injeção para JavaMailSender.
     */
    @Autowired
    private JavaMailSender JavaMailSender;

    /**
     * {@inheritDoc}
     */
    @Override
    public void enviarEmailConfirmacaoPedido(Pedido pedido) {
        SimpleMailMessage sm = preparadorSimpleMailMessageParaPedido(pedido);
        enviarEmail(sm);
    }

    /**
     * Prepara o {@link SimpleMailMessage} para utilização em um {@link Pedido}.
     *
     * @param pedido a ser utilizado.
     *
     * @return o {@link SimpleMailMessage} preparado.
     */
    private SimpleMailMessage preparadorSimpleMailMessageParaPedido(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pedido.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido Confirmado! Código: " + pedido.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());

        return sm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enviarEmailConfirmacaoHtml(Pedido pedido) {
        try {
            MimeMessage mm = preparadorMimeMessageParaPedido(pedido);
            enviarHtmlEmail(mm);
        } catch (MessagingException me) {
            enviarEmailConfirmacaoPedido(pedido);
        }
    }

    /**
     * Prepara o {@link MimeMessage} para utilização em um {@link Pedido}.
     *
     * @param pedido a ser utilizado.
     *
     * @return o {@link MimeMessage} preparado.
     */
    private MimeMessage preparadorMimeMessageParaPedido(Pedido pedido)
            throws MessagingException {
        MimeMessage mm = JavaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm, true);

        mmh.setTo(pedido.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido Confirmado! Código: " + pedido.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(preparadorHtmlParaTemplatePedido(pedido), true);

        return mm;
    }

    /**
     * Prepara o template de email HTML para utilização em um {@link Pedido}.
     *
     * @param pedido a ser utilizado.
     *
     * @return o template preparado.
     */
    private String preparadorHtmlParaTemplatePedido(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);

        return templateEngine.process("email/confirmacaoPedido", context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enviarNovaSenhaPorEmail(Cliente cliente, String novaSenha) {
        SimpleMailMessage sm = preparadorEmailNovaSenha(cliente, novaSenha);
        enviarEmail(sm);
    }

    /**
     * Prepara o {@link SimpleMailMessage} para envio da nova senha.
     *
     * @param cliente que terá a senha alterada.
     * @param novaSenha que será enviada.
     *
     * @return o {@link SimpleMailMessage} preparado.
     */
    private SimpleMailMessage preparadorEmailNovaSenha(Cliente cliente,
            String novaSenha) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(cliente.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + novaSenha);

        return sm;
    }

}
