package com.msilva.cursoSpring.resources;

import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.dto.ClienteDTO;
import com.msilva.cursoSpring.dto.NovoClienteDTO;
import com.msilva.cursoSpring.services.ClienteService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Classe responsável por controlar os métodos de Cliente da API.
 *
 * @author Mateus
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    /**
     * Provê os serviços para {@code Cliente}.
     */
    @Autowired
    private ClienteService service;

    /**
     * Retorna todos os {@code Clientes} cadastrados.
     *
     * @return Uma lista com todos os {@code Cliente} cadastrados.
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscaTodosClientes() {
        List<Cliente> clientes = service.buscaTodosClientes();

        List<ClienteDTO> clientesDTO = clientes.stream()
                .map(cliente -> new ClienteDTO(cliente))
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientesDTO);
    }

    /**
     * O {@code Cliente} com {@code ID}.
     *
     * @param id ID do {@code Cliente} buscado.
     *
     * @return O {@code Cliente} com o {@code ID}.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity buscaClientePorID(@PathVariable Long id) {
        Cliente cliente = service.buscaClientePorID(id);

        return ResponseEntity.ok(cliente);
    }

    /**
     * Retorna todos os clientes cadastrados por pagina.
     *
     * @param pagina O numero da pagina a ser exibida.
     * @param linhasPorPagina A quantidade de linhas por página.
     * @param ordenarPor A ordenação a ser adotada
     * @param direcao A direção a qual os dados serão retornados.
     *
     * @return Uma pagina com os clientes cadastrados.
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> buscaTodasClientesPorPagina(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina,
            @RequestParam(value = "ordenarPor", defaultValue = "nome") String ordenarPor,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
        Page<Cliente> paginaCliente = service.buscaClientePorPagina(
                pagina, linhasPorPagina, ordenarPor, direcao);

        Page<ClienteDTO> pageClienteDTO = paginaCliente
                .map(pag -> new ClienteDTO(pag));

        return ResponseEntity.ok(pageClienteDTO);
    }

    /**
     * Insere um novo Cliente.
     *
     * @param clienteDTO O NovoClienteDTO que será inserido.
     *
     * @return O Corpo da requisição vazio e o código http 201.
     */
    @PostMapping
    public ResponseEntity<Void> inserir(
            @Valid @RequestBody NovoClienteDTO clienteDTO) {
        Cliente categoria = service.retornaClientePorDTO(clienteDTO);

        //Salvo o Cliente.
        categoria = service.inserir(categoria);

        // Monto a URI que foi gerada para esse Cliente.
        // OBS: É uma boa prática retornar a URI do objeto sempre que ele é
        // inserido via API REST.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri();

        // Retorno com o código de status criado (201);
        return ResponseEntity.created(uri).build();
    }

    /**
     * Atualiza o Cliente.
     *
     * @param categoriaDTO O Cliente com o dado atualizado.
     * @param id O ID do Cliente a ser atualizado.
     *
     * @return Um corpo de requisição vazio e o código http 204.
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizar(
            @Valid @RequestBody ClienteDTO categoriaDTO,
            @PathVariable Long id) {
        Cliente categoria = service.retornaClientePorDTO(categoriaDTO);

        categoria.setId(id);
        service.atualizar(categoria);

        return ResponseEntity.noContent().build();
    }

    /**
     * O {@code Cliente} com {@code ID}.
     *
     * @param id ID do {@code Cliente} buscado.
     *
     * @return O {@code Cliente} com o {@code ID}.
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
