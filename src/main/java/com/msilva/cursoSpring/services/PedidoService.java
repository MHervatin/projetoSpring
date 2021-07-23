package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.ItemPedido;
import com.msilva.cursoSpring.domain.PagamentoComBoleto;
import com.msilva.cursoSpring.domain.Pedido;
import com.msilva.cursoSpring.domain.Produto;
import com.msilva.cursoSpring.domain.enums.EstadoPagamento;
import com.msilva.cursoSpring.repositories.ItemPedidoRepository;
import com.msilva.cursoSpring.repositories.PagamentoRepository;
import com.msilva.cursoSpring.repositories.PedidoRepository;
import com.msilva.cursoSpring.services.exceptions.ObjectNotFoundException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provê serviços para {@code Pedido}.
 *
 * @author Mateus
 */
@Service
public class PedidoService {

    /**
     * Provê a instância do repositório.
     */
    @Autowired
    private PedidoRepository repository;

    /**
     * Provê a instância do service de Boleto.
     */
    @Autowired
    private BoletoService boletoService;

    /**
     * Provê a instância do repositório de pagamento.
     */
    @Autowired
    private PagamentoRepository pagamentoRepository;

    /**
     * Provê a instância do repositório de item do pedido.
     */
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    /**
     * Provê a instância do service de produto.
     */
    @Autowired
    private ProdutoService produtoService;

    /**
     * Provê uma instância de service de Cliente;
     */
    @Autowired
    private ClienteService clienteService;

    /**
     * Provê uma instância de service de Email;
     */
    @Autowired
    private EmailService emailService;

    /**
     * Busca todas os {@code Pedidos}.
     *
     * @return Uma lista com todos os Pedidos.
     */
    public List<Pedido> buscaTodosPedidos() {
        return repository.findAll();
    }

    /**
     * O {@code Pedido} com o {@code ID} informado.
     *
     * @param id ID do {@code Pedido} buscado.
     *
     * @return O {@code Pedido} com o {@code ID] informado.
     */
    public Pedido buscaPedidoPorID(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Objeto não encontrado! - ID: '"
                        + id + "', Tipo: '" + Pedido.class.getName() + "'"));
    }

    /**
     * Efetua a inserção de um novo pedido.
     *
     * @param pedido a ser inserido.
     *
     * @return o pedido inserido.
     */
    public Pedido inserir(Pedido pedido) {
        if (pedido.getId() != null) {
            pedido.setId(null);
        }

        pedido.setInstante(new Date());
        pedido.setCliente(
                clienteService.buscaClientePorID(pedido.getCliente().getId()));

        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagamento
                    = (PagamentoComBoleto) pedido.getPagamento();

            boletoService.preencherPagamentoComBoleto(pagamento,
                    pedido.getInstante());
        }

        pedido = repository.save(pedido);

        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = produtoService.buscaProdutoPorID(
                    item.getProduto().getId());

            item.setProduto(produto);
            item.setDesconto(0.00);
            item.setPreco(produto.getPreco());

            item.setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());

        emailService.enviarEmailConfirmacaoHtml(pedido);

        return pedido;
    }
}
