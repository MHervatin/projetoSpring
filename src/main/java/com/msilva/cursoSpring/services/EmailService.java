package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author Mateus
 */
public interface EmailService {

    /**
     * Envia um e-mail de confirmação de um pedido.
     *
     * @param pedido confirmado.
     */
    public void enviarEmailConfirmacaoPedido(Pedido pedido);

    /**
     * Envia um e-mail
     *
     * @param message a ser enviada.
     */
    public void enviarEmail(SimpleMailMessage message);
}
