package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.repositories.ClienteRepository;
import com.msilva.cursoSpring.security.UsuarioSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Provê os serviços de {@code UserDetailsService}.
 *
 * @author Mateus
 */
@Service
public class UsuarioDetalhesServiceImpl implements UserDetailsService {

    /**
     * Provê uma instância do repositório de {@code Cliente}.
     */
    @Autowired
    private ClienteRepository ClienteRepository;

    /**
     * @inheritDoc
     */
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Cliente cliente = ClienteRepository.findByEmail(email);

        if (cliente == null) {
            throw new UsernameNotFoundException("O usuário '" + email
                    + "' não existe");
        }

        return new UsuarioSpringSecurity(cliente.getId(), cliente.getEmail(),
                cliente.getSenha(), cliente.getPerfis());
    }

}
