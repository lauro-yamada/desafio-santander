package br.com.banco.brq.domain.movimentacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    @Query("""
        select m from Movimentacao m
        where 
        m.data between :dataIni and :dataFim
    """)
    Page<Movimentacao> findMovimentacaByData(
            @Param("dataIni") LocalDateTime dataIni,
            @Param("dataFim") LocalDateTime dataFim,
            Pageable pageable);

}
