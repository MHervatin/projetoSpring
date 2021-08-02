package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.repositories.ClienteRepository;
import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Provê serviços de autenticação.
 *
 * @author Mateus
 */
@Service
public class AuthService {

    /**
     * Provê o acesso aos dados de cliente.
     */
    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Provê uma instância de cliente service.
     */
    @Autowired
    private ClienteService clienteService;

    /**
     * Provê uma instância de {@code BCryptPasswordEncoder}.
     */
    @Autowired
    private BCryptPasswordEncoder bcpe;

    /**
     * Provê uma instância de {@code EmailService}.
     */
    @Autowired
    private EmailService emailService;

    /**
     * Provê uma instância de {@code Random}.
     */
    private Random random = new Random();

    /**
     * Envia a nova senha ao Cliente.
     *
     * @param email e-mail em que a senha deve ser enviada.
     */
    public void enviaNovaSenha(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);

        if (cliente == null) {
            throw new ObjectNotFoundException("E-mail não encontrado!");
        }

        String novaSenha = geraSenhaAleatoria();

        cliente.setSenha(bcpe.encode(novaSenha));

        clienteService.atualizar(cliente);

        emailService.enviarNovaSenhaPorEmail(cliente, novaSenha);
    }

    /**
     * Gera a nova senha aleatória.
     *
     * @return a senha gerada.
     */
    private String geraSenhaAleatoria() {
        char[] senhaRandom = new char[24];

        for (int i = 0; i < 24; i++) {
            senhaRandom[i] = randomChar();
        }

        return new String(senhaRandom);
    }

    /**
     * Gera caracteres aleatórios.
     *
     * @return o caracter aleatório.
     */
    private char randomChar() {
        int opcao = random.nextInt(3);

        if (opcao == 0) {
            // Gera digíto
            return (char) (random.nextInt(10) + 48);
        } else if (opcao == 1) {
            // Gera letra maiúscula.
            return (char) (random.nextInt(26) + 65);
        } else {
            // Gera letra minúscula.
            return (char) (random.nextInt(26) + 97);
        }
    }
}
