package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provê o acesso a base de dados para {@code Estado}.
 *
 * @author Mateus
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
