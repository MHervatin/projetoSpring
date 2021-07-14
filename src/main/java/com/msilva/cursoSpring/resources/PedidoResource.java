package com.msilva.cursoSpring.resources;

import com.msilva.cursoSpring.domain.Pedido;
import com.msilva.cursoSpring.services.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping
    public ResponseEntity buscaTodasPedidos() {
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
}
