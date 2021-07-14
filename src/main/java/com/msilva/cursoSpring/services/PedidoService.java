package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Pedido;
import com.msilva.cursoSpring.repositories.PedidoRepository;
import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provê serviços para {@code Pedido}.
 *
 * @author Mateus
 */
@Service
public class PedidoService {

    /**
     * Provê a instância do repositório.
     */
    @Autowired
    private PedidoRepository repository;

    /**
     * Busca todas os {@code Pedidos}.
     *
     * @return Uma lista com todos os Pedidos.
     */
    public List<Pedido> buscaTodosPedidos() {
        return repository.findAll();
    }

    /**
     * O {@code Pedido} com o {@code ID} informado.
     *
     * @param id ID do {@code Pedido} buscado.
     *
     * @return O {@code Pedido} com o {@code ID] informado.
     */
    public Pedido buscaPedidoPorID(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Objeto não encontrado! - ID: '"
                        + id + "', Tipo: '" + Pedido.class.getName() + "'"));
    }
}
