package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provê o acesso a base de dados para {@code Categoria}.
 *
 * @author Mateus
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
