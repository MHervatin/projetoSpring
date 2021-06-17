package com.msilva.cursoSpring.resources;

import com.msilva.cursoSpring.domain.Categoria;
import com.msilva.cursoSpring.services.CategoriaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável por controlar os métodos da API.
 *
 * @author Mateus
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    /**
     * Provê os serviços para {@code Categoria}.
     */
    @Autowired
    private CategoriaService service;

    /**
     * Retorna todas as {@code Categoria} cadastradas.
     *
     * @return Uma lista com todas as {@code Categoria} cadastradas.
     */
    @GetMapping
    public ResponseEntity buscaTodasCategorias() {
        List<Categoria> categorias = service.buscaTodasCategorias();

        return ResponseEntity.ok(categorias);
    }

    /**
     * A {@code Categoria} com {@code ID}.
     *
     * @param id ID da {@code Categoria} buscada.
     *
     * @return A {@code Categoria} com o {@code ID}.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity buscaCategoriaPorID(@PathVariable Long id) {
        Categoria categoria = service.buscaCategoriaPorID(id);

        return ResponseEntity.ok(categoria);
    }
}
