package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provê o acesso a base de dados para {@code Pagamento}.
 *
 * @author Mateus
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
