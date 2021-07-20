package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Cidade;
import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.domain.Endereco;
import com.msilva.cursoSpring.domain.enums.TipoCliente;
import com.msilva.cursoSpring.dto.ClienteDTO;
import com.msilva.cursoSpring.dto.NovoClienteDTO;
import com.msilva.cursoSpring.repositories.CidadeRepository;
import com.msilva.cursoSpring.repositories.ClienteRepository;
import com.msilva.cursoSpring.repositories.EnderecoRepository;
import com.msilva.cursoSpring.services.exceptions.DataIntegrityException;
import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import java.util.List;
import javax.transaction.Transactional;
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

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

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
     * @param cliente O Cliente a ser inserido.
     *
     * @return O Cliente inserido.
     */
    public Cliente inserir(Cliente cliente) {
        if (cliente.getId() != null) {
            cliente.setId(null);
        }
        Cliente clientePersistido = repository.save(cliente);

        enderecoRepository.saveAll(cliente.getEnderecos());

        return clientePersistido;
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
     * Converte um {@code ClienteDTO} para um {@code Cliente}.
     *
     * @param clienteDTO O {@code ClienteDTO} a ser convertido.
     *
     * @return Um {@code Cliente}.
     */
    @Transactional
    public Cliente retornaClientePorDTO(NovoClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(null, clienteDTO.getNome(),
                clienteDTO.getEmail(), clienteDTO.getCpfCnpj(),
                TipoCliente.toEnum(clienteDTO.getTipo()));

        Cidade cidade = cidadeRepository.findById(clienteDTO.getCidadeId()).get();

        Endereco endereco = new Endereco(null, clienteDTO.getLogradouro(),
                clienteDTO.getNumero(), clienteDTO.getComplemento(),
                clienteDTO.getBairro(), clienteDTO.getCep(), cliente, cidade);

        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteDTO.getTelefone1());

        if (clienteDTO.getTelefone2() != null) {
            cliente.getTelefones().add(clienteDTO.getTelefone2());
        }

        if (clienteDTO.getTelefone3() != null) {
            cliente.getTelefones().add(clienteDTO.getTelefone3());
        }

        return cliente;
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
