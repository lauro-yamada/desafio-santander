package br.com.banco.brq.domain.movimentacao;

import br.com.banco.brq.domain.cliente.Cliente;

import java.math.BigDecimal;

public class CobrancaSemTaxaAdministracao  extends  CobrancaTaxaAdministracao{

    public CobrancaSemTaxaAdministracao() {
        super(null);
    }

    @Override
    public BigDecimal calcular(DadosMovimentacao dados, Cliente cliente) {
        System.out.println("valor 0: " + dados.getValor());
        return BigDecimal.ZERO;
    }

}
