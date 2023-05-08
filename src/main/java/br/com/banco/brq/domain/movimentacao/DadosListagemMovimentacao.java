package br.com.banco.brq.domain.movimentacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosListagemMovimentacao {
    private Long id;
    private BigDecimal valor;
    private TipoMovimentacao tipo;
    private LocalDateTime data;
    private BigDecimal valorTaxaAdm;

    public DadosListagemMovimentacao(Movimentacao dados) {
        this.id = dados.getIdMovimentacao();
        this.valor = dados.getValor();
        this.tipo = dados.getTipo();
        this.data = dados.getData();
        this.valorTaxaAdm = dados.getValorTaxaAdm();
    }

}
