package com.msilva.cursoSpring.resources;

import com.msilva.cursoSpring.domain.Categoria;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
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
     * Teste de listagem de categorias.
     *
     * @return Uma lista com as categorias de teste.
     */
    @GetMapping
    public List<Categoria> listar() {
        Categoria cat1 = new Categoria(Long.parseLong("1"), "Informática");
        Categoria cat2 = new Categoria(Long.parseLong("2"), "Mercado");

        ArrayList<Categoria> categorias = new ArrayList<>();
        categorias.add(cat1);
        categorias.add(cat2);

        return categorias;
    }
}
