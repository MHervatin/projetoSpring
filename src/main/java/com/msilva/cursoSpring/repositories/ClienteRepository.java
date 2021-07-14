package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provê o acesso a base de dados para {@code Cliente}.
 *
 * @author Mateus
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
