package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provê o acesso a base de dados para {@code Pedido}.
 *
 * @author Mateus
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /**
     * Busca pedidos por cliente.
     *
     * @param cliente a ser utilizado para busca de pedidos.
     * @param pageRequest que retornará os pedidos paginados.
     *
     * @return os pedidos ligados ao cliente referênciado.
     */
    @Transactional(readOnly = true)
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
