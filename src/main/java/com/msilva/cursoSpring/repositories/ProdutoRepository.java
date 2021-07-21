package com.msilva.cursoSpring.repositories;

import com.msilva.cursoSpring.domain.Categoria;
import com.msilva.cursoSpring.domain.Produto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provê o acesso a base de dados para {@code Categoria}.
 *
 * @author Mateus
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    /**
     * Busca o Produto através do nome e das categorias informadas.
     *
     * @param nome O nome do produto buscado.
     * @param categorias As categorias ao qual o mesmo deve pertencer.
     * @param pageRequest Os parâmetros de página utilizados.
     *
     * @return Uma página com as caracteristicas informadas e com o Produto
     * buscado.
     * 
     * @see  https://docs.spring.io/spring-data/jpa/docs/2.5.3/api/
     */
    @Transactional(readOnly = true)
    public Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
            String nome, List<Categoria> categorias, Pageable pageRequest);
}
