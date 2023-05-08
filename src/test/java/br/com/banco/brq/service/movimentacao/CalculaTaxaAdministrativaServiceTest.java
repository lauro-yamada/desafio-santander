package br.com.banco.brq.service.movimentacao;

import br.com.banco.brq.domain.cliente.ClienteRepository;
import br.com.banco.brq.domain.movimentacao.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CalculaTaxaAdministrativaServiceTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Deveria devolver true quando não for Plano Exclusive e valor valor do saque que 300")
    void calcularValorTaxaNaoExclusiveValorMaiorQue300() {
        DadosMovimentacao dados = new DadosMovimentacao(TipoMovimentacao.DEPOSITO, new BigDecimal("500"), 1l);

        var cliente = clienteRepository.getReferenceById(dados.getIdCliente());

        CobrancaTaxaAdministracao taxaAdministracao =
                new CobrancaTaxaAdministrativaPlanoExclusive(
                        new CobrancaTxaAdministracaoFaixaInicial(
                                new CobrancaTaxaAdministracaoFinal(
                                        new CobrancaSemTaxaAdministracao())));

        BigDecimal taxa =  taxaAdministracao.calcular(dados, cliente);

        assertThat(taxa).isEqualTo(new BigDecimal("5.00"));

    }

    @Test
    @DisplayName("Deveria devolver true quando não for Plano Exclusive e valor do saque for maior que 100 e menor ou igual a 300")
    void calcularValorTaxaNaoExclusiveValorMaiorQue100EMenorOuIgualQue300() {
        DadosMovimentacao dados = new DadosMovimentacao(TipoMovimentacao.DEPOSITO, new BigDecimal("200"), 1l);

        var cliente = clienteRepository.getReferenceById(dados.getIdCliente());

        CobrancaTaxaAdministracao taxaAdministracao =
                new CobrancaTaxaAdministrativaPlanoExclusive(
                        new CobrancaTxaAdministracaoFaixaInicial(
                                new CobrancaTaxaAdministracaoFinal(
                                        new CobrancaSemTaxaAdministracao())));

        BigDecimal taxa =  taxaAdministracao.calcular(dados, cliente);

        assertThat(taxa).isEqualTo(dados.getValor().multiply(new BigDecimal("0.004")));

    }

    @Test
    @DisplayName("Deveria devolver true quando não for Plano Exclusive e valor do saque for menor ou igual a 100")
    void calcularValorTaxaNaoExclusiveValorMenorOuIgualA100() {
        DadosMovimentacao dados = new DadosMovimentacao(TipoMovimentacao.DEPOSITO, new BigDecimal("100"), 2l);

        var cliente = clienteRepository.getReferenceById(dados.getIdCliente());

        CobrancaTaxaAdministracao taxaAdministracao =
                new CobrancaTaxaAdministrativaPlanoExclusive(
                        new CobrancaTxaAdministracaoFaixaInicial(
                                new CobrancaTaxaAdministracaoFinal(
                                        new CobrancaSemTaxaAdministracao())));

        BigDecimal taxa =  taxaAdministracao.calcular(dados, cliente);

        assertThat(taxa).isEqualTo(BigDecimal.ZERO);

    }

    @Test
    @DisplayName("Deveria devolver true quando for Plano Exclusive e valor qualquer")
    void calcularValorTaxaExclusiveQualquerValor() {
        DadosMovimentacao dados = new DadosMovimentacao(TipoMovimentacao.DEPOSITO, new BigDecimal("500"), 1l);

        var cliente = clienteRepository.getReferenceById(dados.getIdCliente());

        CobrancaTaxaAdministracao taxaAdministracao =
                new CobrancaTaxaAdministrativaPlanoExclusive(
                        new CobrancaTxaAdministracaoFaixaInicial(
                                new CobrancaTaxaAdministracaoFinal(
                                        new CobrancaSemTaxaAdministracao())));

        BigDecimal taxa =  taxaAdministracao.calcular(dados, cliente);

        assertThat(taxa).isEqualTo(BigDecimal.ZERO);

    }


}