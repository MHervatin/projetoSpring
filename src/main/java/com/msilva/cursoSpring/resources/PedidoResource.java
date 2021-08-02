package com.msilva.cursoSpring.resources;

import com.msilva.cursoSpring.domain.Pedido;
import com.msilva.cursoSpring.services.PedidoService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Classe responsável por controlar os métodos da API.
 *
 * @author Mateus
 */
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    /**
     * Provê os serviços para {@code Pedido}.
     */
    @Autowired
    private PedidoService service;

    /**
     * Retorna todos os {@code Pedido} cadastrados.
     *
     * @return Uma lista com todos os {@code Pedidos} cadastrados.
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity buscaTodosPedidos() {
        List<Pedido> pedidos = service.buscaTodosPedidos();

        return ResponseEntity.ok(pedidos);
    }

    /**
     * O {@code Pedido} com {@code ID}.
     *
     * @param id ID do {@code Pedido} buscado.
     *
     * @return O {@code Pedido} com o {@code ID}.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity buscaPedidoPorID(@PathVariable Long id) {
        Pedido pedido = service.buscaPedidoPorID(id);

        return ResponseEntity.ok(pedido);
    }

    /**
     * Insere um novo Pedido.
     *
     * @param pedido O pedido que será inserido.
     *
     * @return O Corpo da requisição vazio e o código http 201.
     */
    @PostMapping
    public ResponseEntity<Void> inserir(@Valid @RequestBody Pedido pedido) {

        //Salvo o pedido.
        pedido = service.inserir(pedido);

        // Monto a URI que foi gerada para esse pedido.
        // OBS: É uma boa prática retornar a URI do objeto sempre que ele é
        // inserido via API REST.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pedido.getId()).toUri();

        // Retorno com o código de status criado (201);
        return ResponseEntity.created(uri).build();
    }

    /**
     * Retorna todos os pedidos cadastrados para o cliente por página.
     *
     * @param pagina O numero da pagina a ser exibida.
     * @param linhasPorPagina A quantidade de linhas por página.
     * @param ordenarPor A ordenação a ser adotada
     * @param direcao A direção a qual os dados serão retornados.
     *
     * @return Uma página com os pedidos cadastrados.
     */
    @GetMapping(value = "/page")
    public ResponseEntity<Page<Pedido>> buscaTodosPedidosPorPagina(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina,
            @RequestParam(value = "ordenarPor", defaultValue = "instante") String ordenarPor,
            @RequestParam(value = "direcao", defaultValue = "DESC") String direcao) {
        Page<Pedido> paginaPedido = service.buscaPedidoPorPagina(
                pagina, linhasPorPagina, ordenarPor, direcao);

        return ResponseEntity.ok(paginaPedido);
    }
}
