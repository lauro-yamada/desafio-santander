package br.com.banco.brq.domain.movimentacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosMovimentacao {
    @NotNull
    private TipoMovimentacao tipo;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private Long idCliente;
}
