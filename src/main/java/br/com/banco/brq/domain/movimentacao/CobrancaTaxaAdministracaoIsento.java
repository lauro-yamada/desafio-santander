package br.com.banco.brq.domain.movimentacao;

import br.com.banco.brq.domain.cliente.Cliente;
import br.com.banco.brq.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class CobrancaTaxaAdministracaoIsento extends CobrancaTaxaAdministracao{

    @Autowired
    private ClienteRepository clienteRepository;

    public CobrancaTaxaAdministracaoIsento(CobrancaTaxaAdministracao proximo) {
        super(proximo);
    }

    public BigDecimal calcular(DadosMovimentacao dados, Cliente cliente){

        if(dados.getValor().compareTo(new BigDecimal("100")) <= 0){
            return BigDecimal.ZERO;
        }

        return proximo.calcular(dados, cliente);

    }
}
