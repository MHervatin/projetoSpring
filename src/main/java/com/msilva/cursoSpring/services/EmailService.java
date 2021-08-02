package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.domain.Pedido;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

/**
 * Provê serviços para e-mail.
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
     * Envia um e-mail.
     *
     * @param message a ser enviada.
     */
    public void enviarEmail(SimpleMailMessage message);

    /**
     * Envia um e-mail HTML de confirmação de um pedido.
     *
     * @param pedido confirmado.
     */
    public void enviarEmailConfirmacaoHtml(Pedido pedido);

    /**
     * Envia um e-mail HTML.
     *
     * @param message a ser enviada.
     */
    public void enviarHtmlEmail(MimeMessage message);

    /**
     * Envia um e-mail com a nova senha.
     *
     * @param cliente que terá sua senha atualizada.
     * @param novaSenha que será utilizada.
     */
    public void enviarNovaSenhaPorEmail(Cliente cliente, String novaSenha);
}
