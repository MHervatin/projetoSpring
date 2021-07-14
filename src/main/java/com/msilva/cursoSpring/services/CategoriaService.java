package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.Categoria;
import com.msilva.cursoSpring.repositories.CategoriaRepository;
import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
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
}
