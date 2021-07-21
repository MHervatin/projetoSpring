package com.msilva.cursoSpring.services;

import com.msilva.cursoSpring.domain.PagamentoComBoleto;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * Provê serviços para Boleto.
 *
 * @author Mateus
 */
@Service
public class BoletoService {

    /**
     * Preenche os dados de vencimento do Pagamento com Boleto.
     *
     * @param pagamento O Pagamento.
     * @param instanteDoPedido Instante do Pedido.
     */
    public void preencherPagamentoComBoleto(PagamentoComBoleto pagamento,
            Date instanteDoPedido) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instanteDoPedido);
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        pagamento.setDataVencimento(calendar.getTime());
    }
}
