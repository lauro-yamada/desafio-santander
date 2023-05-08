package br.com.banco.brq.domain.movimentacao;

import br.com.banco.brq.domain.cliente.Cliente;

import java.math.BigDecimal;

public class CobrancaTxaAdministracaoFaixaInicial extends CobrancaTaxaAdministracao{

    public CobrancaTxaAdministracaoFaixaInicial(CobrancaTaxaAdministracao proximo) {
        super(proximo);
    }

    public BigDecimal calcular(DadosMovimentacao dados, Cliente cliente){
        var cobrarTaxa = dados.getValor().compareTo(new BigDecimal("100")) > 0
                && dados.getValor().compareTo(new BigDecimal("300")) <= 0;
        if(cobrarTaxa){
            return dados.getValor().multiply(new BigDecimal("0.004"));
        }

        return proximo.calcular(dados, cliente);

    }

}
