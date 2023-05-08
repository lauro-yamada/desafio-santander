package br.com.banco.brq.domain.movimentacao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MovimentacaoRepositoryTest {

    @Autowired
    MovimentacaoRepository movimentacaoRepository;
    @Test
    @DisplayName("Deveria devolver true quando não tiver dados para a data passada como parametro")
    void findMovimentacaByDataSemDadosDataEscolhida() {
        LocalDateTime dataIni = LocalDate.of(2017, 12, 25).atTime(0, 0);
        LocalDateTime dataFim = LocalDate.of(2017, 12, 25).atTime(23, 59);
        PageRequest pageRequest = PageRequest.of(
                0,
                10,
                Sort.Direction.ASC,
                "data");
        var movimentacao = movimentacaoRepository.findMovimentacaByData(dataIni, dataFim, pageRequest);

        assertThat(movimentacao).isEmpty();

    }

    @Test
    @DisplayName("Deveria devolver true quando tiver dados para a data passada como parametro")
    void findMovimentacaByDataComDadosDataEscolhida() {
        LocalDateTime dataIni = LocalDate.of(2023, 5, 07).atTime(0, 0);
        LocalDateTime dataFim = LocalDate.of(2023, 5, 07).atTime(23, 59);
        PageRequest pageRequest = PageRequest.of(
                0,
                10,
                Sort.Direction.ASC,
                "data");
        var movimentacao = movimentacaoRepository.findMovimentacaByData(dataIni, dataFim, pageRequest);

        assertThat(movimentacao).isNotEmpty();

    }
}