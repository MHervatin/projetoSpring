package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Pedido;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author Mateus
 */
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

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
        sm.setSubject("Seu pedido de código '" + pedido.getId()
                + "' foi confirmado!");

        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());

        return sm;
    }
}
