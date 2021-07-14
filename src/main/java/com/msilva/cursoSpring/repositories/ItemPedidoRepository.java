package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provê o acesso a base de dados para {@code ItemPedido}.
 *
 * @author Mateus
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
