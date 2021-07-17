package com.msilva.cursoSpring.resources;

import com.msilva.cursoSpring.domain.Categoria;
import com.msilva.cursoSpring.dto.CategoriaDTO;
import com.msilva.cursoSpring.services.CategoriaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
     * Retorna todas as categoria cadastradas.
     *
     * @return Uma lista de {@code CategoriaDTO} com todas as categorias
     * cadastradas.
     */
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> buscaTodasCategorias() {
        List<Categoria> categorias = service.buscaTodasCategorias();

        List<CategoriaDTO> categoriasDto = categorias.stream()
                .map(cat -> new CategoriaDTO(cat))
                .collect(Collectors.toList());

        return ResponseEntity.ok(categoriasDto);
    }

    /**
     * Retorna todas as categoria cadastradas por pagina.
     *
     * @param pagina O numero da pagina a ser exibida.
     * @param linhasPorPagina A quantidade de linhas por página.
     * @param ordenarPor A ordenação a ser adotada
     * @param direcao A direção a qual os dados serão retornados.
     *
     * @return Uma pagina com as categorias cadastradas.
     */
    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDTO>> buscaTodasCategoriasPorPagina(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina,
            @RequestParam(value = "ordenarPor", defaultValue = "nome") String ordenarPor,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
        Page<Categoria> paginaCategoria = service.buscaCategoriaPorPagina(
                pagina, linhasPorPagina, ordenarPor, direcao);

        Page<CategoriaDTO> pageCategoriaDTO = paginaCategoria
                .map(pag -> new CategoriaDTO(pag));

        return ResponseEntity.ok(pageCategoriaDTO);
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

    /**
     * Insere uma nova Categoria.
     *
     * @param categoria A Categoria que será inserida.
     *
     * @return O Corpo da requisição vazio e o código http 201.
     */
    @PostMapping
    public ResponseEntity<Void> inserir(@RequestBody Categoria categoria) {
        //Salvo a categoria.
        categoria = service.inserir(categoria);

        // Monto a URI que foi gerada para essa categoria.
        // OBS: É uma boa prática retornar a URI do objeto sempre que ele é
        // inserido via API REST.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri();

        // Retorno com o código de status criado (201);
        return ResponseEntity.created(uri).build();
    }

    /**
     * Atualiza a Categoria.
     *
     * @param categoria A Categoria com o dado atualizado.
     * @param id O ID da Categoria a ser atualizada.
     *
     * @return Um corpo de requisição vazio e o código http 204.
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody Categoria categoria,
            @PathVariable Long id) {
        categoria.setId(id);
        service.atualizar(categoria);

        return ResponseEntity.noContent().build();
    }

    /**
     * A {@code Categoria} com {@code ID}.
     *
     * @param id ID da {@code Categoria} buscada.
     *
     * @return A {@code Categoria} com o {@code ID}.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
