package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provê o acesso a base de dados para {@code Endereco}.
 *
 * @author Mateus
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
