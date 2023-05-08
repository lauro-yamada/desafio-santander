package br.com.banco.brq.domain.movimentacao;

import br.com.banco.brq.domain.cliente.Cliente;
import br.com.banco.brq.domain.movimentacao.CobrancaTaxaAdministracao;
import br.com.banco.brq.domain.movimentacao.DadosMovimentacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public class CobrancaTaxaAdministrativaPlanoExclusive extends CobrancaTaxaAdministracao {

    public CobrancaTaxaAdministrativaPlanoExclusive(CobrancaTaxaAdministracao proximo) {
        super(proximo);
    }

    public BigDecimal calcular(DadosMovimentacao dados, Cliente cliente){
        if(cliente.getConta().isPlanoExclusive()){
            return BigDecimal.ZERO;
        }

        return proximo.calcular(dados, cliente);

    }
}
