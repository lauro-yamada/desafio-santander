package br.com.banco.brq.domain.movimentacao;

import br.com.banco.brq.domain.cliente.Cliente;

import java.math.BigDecimal;

public abstract class CobrancaTaxaAdministracao {

    protected CobrancaTaxaAdministracao proximo;

    public CobrancaTaxaAdministracao(CobrancaTaxaAdministracao proximo){
        this.proximo = proximo;
    }

    public abstract BigDecimal calcular(DadosMovimentacao dados, Cliente cliente);

}
