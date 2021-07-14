package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provê o acesso a base de dados para {@code Pedido}.
 *
 * @author Mateus
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
