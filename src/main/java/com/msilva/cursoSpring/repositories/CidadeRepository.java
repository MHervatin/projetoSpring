package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provê o acesso a base de dados para {@code Cidade}.
 *
 * @author Mateus
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
