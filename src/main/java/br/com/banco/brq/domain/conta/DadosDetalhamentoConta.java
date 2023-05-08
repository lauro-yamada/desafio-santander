package br.com.banco.brq.domain.conta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosDetalhamentoConta extends DadosListagemConta {

    private String numeroConta;
    private BigDecimal saldo;
    private boolean planoExclusive;
    public DadosDetalhamentoConta(Conta dados) {
            this.numeroConta = dados.getNumeroConta();
            this.saldo = dados.getSaldo();
            this.planoExclusive = dados.isPlanoExclusive();
    }
}
