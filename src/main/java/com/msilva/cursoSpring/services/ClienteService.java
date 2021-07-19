package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.domain.enums.TipoCliente;
import com.msilva.cursoSpring.dto.ClienteDTO;
import com.msilva.cursoSpring.repositories.ClienteRepository;
import com.msilva.cursoSpring.services.exceptions.DataIntegrityException;
import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
     * O {@code Cliente} com o {@code ID} informado.
     *
     * @param id ID do {@code Cliente} buscado.
     *
     * @return AO{@code Cliente} com o {@code ID] informado.
     */
    public Cliente buscaClientePorID(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Objeto não encontrado! - ID: '"
                        + id + "', Tipo: '" + Cliente.class.getName() + "'"));
    }

    /**
     * Insere um novo Cliente.
     *
     * @param categoria O Cliente a ser inserido.
     *
     * @return O Cliente inserido.
     */
    public Cliente inserir(Cliente categoria) {
        if (categoria.getId() != null) {
            categoria.setId(null);
        }

        return repository.save(categoria);
    }

    /**
     * Atualiza o cliente.
     *
     * @param clienteAtualizado O Cliente a ser atualizado.
     *
     * @return O Cliente atualizado.
     */
    public Cliente atualizar(Cliente clienteAtualizado) {
        Cliente clienteAtual = buscaClientePorID(clienteAtualizado.getId());

        atualizarDados(clienteAtual, clienteAtualizado);

        return repository.save(clienteAtual);
    }

    /**
     * Deleta o {@code Cliente} com o {@code ID}.
     *
     * @param id ID do {@code Cliente} buscado.
     *
     */
    public void deletarPorId(Long id) {
        buscaClientePorID(id);

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException dex) {
            throw new DataIntegrityException("Não é possivel excluir um"
                    + "Cliente, pois existem entidades relacionadas", dex);
        }
    }

    /**
     * Busca paginada para Cliente.
     *
     * @param pagina O numero da pagina a ser exibida.
     * @param linhasPorPagina A quantidade de linhas por página.
     * @param ordenarPor A ordenação a ser adotada
     * @param direcao A direção a qual os dados serão retornados.
     *
     * @return Uma página de Cliente.
     */
    public Page<Cliente> buscaClientePorPagina(Integer pagina,
            Integer linhasPorPagina,
            String ordenarPor, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina,
                Direction.valueOf(direcao), ordenarPor);

        return repository.findAll(pageRequest);
    }

    /**
     * Converte um {@code ClienteDTO} para um {@code Cliente}.
     *
     * @param clienteDTO O {@code ClienteDTO} a ser convertido.
     *
     * @return Um {@code Cliente}.
     */
    public Cliente retornaClientePorDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(),
                clienteDTO.getEmail(), null, null);
    }

    /**
     * Atualiza os dados do {@code Cliente} atual com o do {@code Cliente}
     * atualizado.
     *
     * @param clienteAtual O cliente a ser atualizado.
     * @param clienteAtualizado O cliente com os dados atualizados.
     */
    private void atualizarDados(Cliente clienteAtual,
            Cliente clienteAtualizado) {
        clienteAtual.setNome(clienteAtualizado.getNome());
        clienteAtual.setEmail(clienteAtualizado.getEmail());
    }
}
