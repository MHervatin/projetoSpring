package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.repositories.ClienteRepository;
import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provê serviços para {@code Cliente}.
 *
 * @author Mateus
 */
@Service
public class ClienteService {

    /**
     * Provê a instância do repositório.
     */
    @Autowired
    private ClienteRepository repository;

    /**
     * Busca todos os {@code Cliente}.
     *
     * @return Uma lista com todos os Clientes.
     */
    public List<Cliente> buscaTodosClientes() {
        return repository.findAll();
    }

    /**
     * A {@code Cliente} com o {@code ID} informado.
     *
     * @param id ID do {@code Cliente} buscado.
     *
     * @return A {@code Cliente} com o {@code ID] informado.
     */
    public Cliente buscaClientePorID(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Objeto não encontrado! - ID: '"
                        + id + "', Tipo: '" + Cliente.class.getName() + "'"));
    }
}
