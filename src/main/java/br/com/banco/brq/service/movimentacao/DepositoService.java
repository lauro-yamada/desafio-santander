package br.com.banco.brq.service.movimentacao;

import br.com.banco.brq.domain.cliente.Cliente;
import br.com.banco.brq.domain.cliente.ClienteRepository;
import br.com.banco.brq.domain.exceptions.ValidacaoException;
import br.com.banco.brq.domain.movimentacao.DadosMovimentacao;
import br.com.banco.brq.domain.movimentacao.Movimentacao;
import br.com.banco.brq.domain.movimentacao.MovimentacaoRepository;
import br.com.banco.brq.domain.movimentacao.TipoMovimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class DepositoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    //private
    public Movimentacao depositar(DadosMovimentacao dados){
        var cliente = clienteRepository.findById(dados.getIdCliente());
        if (cliente.isEmpty()) {
            throw new ValidacaoException("Cliente não cadastrado!");
        }
        var  novoSaldo = cliente.get().getConta().getSaldo().add(dados.getValor());
        cliente.get().getConta().alterarValorSaldo(novoSaldo);
        return movimentacaoRepository.save(new Movimentacao(cliente.get(), dados, BigDecimal.ZERO));

    }

}
