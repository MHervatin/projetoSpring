package com.msilva.cursoSpring.resources;

import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.services.ClienteService;
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
    @GetMapping
    public ResponseEntity buscaTodasClientes() {
        List<Cliente> clientes = service.buscaTodosClientes();

        return ResponseEntity.ok(clientes);
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
}
