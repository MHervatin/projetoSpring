package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Categoria;
import com.msilva.cursoSpring.domain.Produto;
import com.msilva.cursoSpring.repositories.CategoriaRepository;
import com.msilva.cursoSpring.repositories.ProdutoRepository;
import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Provê serviços para {@code Produto}.
 *
 * @author Mateus
 */
@Service
public class ProdutoService {

    /**
     * Provê a instância do repositório.
     */
    @Autowired
    private ProdutoRepository repository;

    /**
     *
     */
    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * O {@code Produto} com o {@code ID} informado.
     *
     * @param id ID do {@code Produto} buscado.
     *
     * @return O {@code Produto} com o {@code ID] informado.
     */
    public Produto buscaProdutoPorID(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Objeto não encontrado! - ID: '"
                        + id + "', Tipo: '" + Produto.class.getName() + "'"));
    }

    /**
     * Retorna a página do produto com o nome e categorias buscadas.
     *
     * @param nome Nome do produto buscado.
     * @param idsCategorias IDs das categorias que o produto pode pertencer.
     * @param pagina O numero da pagina a ser exibida.
     * @param linhasPorPagina A quantidade de linhas por página.
     * @param ordenarPor A ordenação a ser adotada
     * @param direcao A direção a qual os dados serão retornados.
     *
     * @return Uma pagina com o produto buscado.
     */
    public Page<Produto> buscaProdutoPorNomeECategorias(String nome,
            List<Long> idsCategorias, Integer pagina, Integer linhasPorPagina,
            String ordenarPor, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina,
                Sort.Direction.valueOf(direcao), ordenarPor);

        List<Categoria> categorias
                = categoriaRepository.findAllById(idsCategorias);

        return repository.findDistinctByNomeContainingAndCategoriasIn(
                nome, categorias, pageRequest);

    }
}
