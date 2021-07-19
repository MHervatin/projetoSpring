package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Categoria;
import com.msilva.cursoSpring.dto.CategoriaDTO;
import com.msilva.cursoSpring.repositories.CategoriaRepository;
import com.msilva.cursoSpring.services.exceptions.DataIntegrityException;
import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * Provê serviços para {@code Categoria}.
 *
 * @author Mateus
 */
@Service
public class CategoriaService {

    /**
     * Provê a instância do repositório.
     */
    @Autowired
    private CategoriaRepository repository;

    /**
     * Busca todas as {@code Categoria}.
     *
     * @return Uma lista com todas as Categorias.
     */
    public List<Categoria> buscaTodasCategorias() {
        return repository.findAll();
    }

    /**
     * A {@code Categoria} com o {@code ID} informado.
     *
     * @param id ID da {@code Categoria} buscada.
     *
     * @return A {@code Categoria} com o {@code ID] informado.
     */
    public Categoria buscaCategoriaPorID(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Objeto não encontrado! - ID: '"
                        + id + "', Tipo: '" + Categoria.class.getName() + "'"));
    }

    /**
     * Insere uma nova Categoria.
     *
     * @param categoria A Categoria a ser inserida.
     *
     * @return A Categoria inserida.
     */
    public Categoria inserir(Categoria categoria) {
        if (categoria.getId() != null) {
            categoria.setId(null);
        }

        return repository.save(categoria);
    }

    /**
     * Atualiza a categoria.
     *
     * @param categoria A Categoria a ser atualizada
     *
     * @return A Categoria atualizada.
     */
    public Categoria atualizar(Categoria categoria) {
        buscaCategoriaPorID(categoria.getId());

        return repository.save(categoria);
    }

    /**
     * Deleta a {@code Categoria} com o {@code ID}.
     *
     * @param id ID da {@code Categoria} buscada.
     *
     */
    public void deletarPorId(Long id) {
        buscaCategoriaPorID(id);

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException dex) {
            throw new DataIntegrityException("Não é possivel excluir uma"
                    + " categoria que contém produtos.", dex);
        }
    }

    /**
     * Busca paginada para categoria
     *
     * @param pagina O numero da pagina a ser exibida.
     * @param linhasPorPagina A quantidade de linhas por página.
     * @param ordenarPor A ordenação a ser adotada
     * @param direcao A direção a qual os dados serão retornados.
     *
     * @return Uma página de Categoria.
     */
    public Page<Categoria> buscaCategoriaPorPagina(Integer pagina,
            Integer linhasPorPagina,
            String ordenarPor, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina,
                Direction.valueOf(direcao), ordenarPor);

        return repository.findAll(pageRequest);
    }

    /**
     * Converte uma {@code CategoriaDTO} para uma {@code Categoria}.
     *
     * @param categoriaDTO A {@code CategoriaDTO} a ser convertida.
     *
     * @return Uma {@code Categoria}.
     */
    public Categoria retornaCategoriaPorDTO(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }
}
