package br.com.banco.brq.service.movimentacao;

import br.com.banco.brq.domain.cliente.ClienteRepository;
import br.com.banco.brq.domain.exceptions.ValidacaoException;
import br.com.banco.brq.domain.movimentacao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaqueService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private CalculaTaxaAdministrativaService calculaTaxaAdministrativaService;
    public Movimentacao sacar(DadosMovimentacao dados) {
        var cliente = clienteRepository.findById(dados.getIdCliente());
        if (cliente.isEmpty()) {
            throw new ValidacaoException("Cliente não cadastrado!");
        }
        BigDecimal valorTaxaAdministracao = calculaTaxaAdministrativaService.calcular(dados);
        var  novoSaldo = cliente.get().getConta().getSaldo().subtract(valorTaxaAdministracao).subtract(dados.getValor());
        cliente.get().getConta().alterarValorSaldo(novoSaldo);
        return movimentacaoRepository.save(new Movimentacao(cliente.get(), dados, valorTaxaAdministracao));
    }

}
