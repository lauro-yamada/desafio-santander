package br.com.banco.brq.domain.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DadosCadastroConta {

    @NotBlank
    private String nuumeroConta;
    @NotNull
    private BigDecimal saldo;
    @NotNull
    private boolean planoExclusive;

}
