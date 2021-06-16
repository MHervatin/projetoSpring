package com.msilva.cursoSpring.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mateus
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @GetMapping
    public String listar() {
        return "Rest está funcionando";
    }
}
