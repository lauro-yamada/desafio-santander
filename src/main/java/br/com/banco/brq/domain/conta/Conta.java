package br.com.banco.brq.domain.conta;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "numeroConta")
public class Conta {

    private String numeroConta;
    private BigDecimal saldo;
    private boolean planoExclusive;

    public Conta(DadosCadastroConta dados) {
        this.numeroConta = dados.getNuumeroConta();
        this.saldo = dados.getSaldo();
        this.planoExclusive = dados.isPlanoExclusive();
    }

    public void alterarValorSaldo(BigDecimal novoSaldo) {
        this.saldo = novoSaldo;
    }
}
