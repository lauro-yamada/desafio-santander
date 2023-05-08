package br.com.banco.brq.domain.movimentacao;

import br.com.banco.brq.domain.cliente.Cliente;
import br.com.banco.brq.domain.movimentacao.CobrancaTaxaAdministracao;
import br.com.banco.brq.domain.movimentacao.DadosMovimentacao;

import java.math.BigDecimal;

public class CobrancaTaxaAdministracaoFinal extends CobrancaTaxaAdministracao {

    public CobrancaTaxaAdministracaoFinal(CobrancaTaxaAdministracao proximo) {
        super(proximo);
    }

    public BigDecimal calcular(DadosMovimentacao dados, Cliente cliente){
        var cobrarTaxa = dados.getValor().compareTo(new BigDecimal("300")) > 0;
        if(cobrarTaxa){
            return dados.getValor().multiply(new BigDecimal("0.01"));
        }

        return proximo.calcular(dados, cliente);

    }

}
