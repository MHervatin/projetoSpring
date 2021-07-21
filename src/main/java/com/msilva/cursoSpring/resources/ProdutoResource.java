package com.msilva.cursoSpring.resources;

import com.msilva.cursoSpring.domain.Produto;
import com.msilva.cursoSpring.dto.ProdutoDTO;
import com.msilva.cursoSpring.resources.utils.URLUtilities;
import com.msilva.cursoSpring.services.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável por controlar os métodos da API.
 *
 * @author Mateus
 */
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    /**
     * Provê os serviços para {@code Produto}.
     */
    @Autowired
    private ProdutoService service;

    /**
     * O {@code Produto} com {@code ID}.
     *
     * @param id ID do {@code Produto} buscado.
     *
     * @return O {@code Produto} com o {@code ID}.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity buscaProdutoPorID(@PathVariable Long id) {
        Produto Produto = service.buscaProdutoPorID(id);

        return ResponseEntity.ok(Produto);
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
    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String idsCategorias,
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linhasPorPagina,
            @RequestParam(value = "orderBy", defaultValue = "nome") String ordenarPor,
            @RequestParam(value = "direction", defaultValue = "ASC") String direcao) {
        String nomeDecoded = URLUtilities.decodificarParametroString(nome);
        List<Long> ids = URLUtilities.converteStringEmListaLong(idsCategorias);

        Page<Produto> list = service.buscaProdutoPorNomeECategorias(
                nomeDecoded, ids, pagina, linhasPorPagina, ordenarPor, direcao);

        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }
}
