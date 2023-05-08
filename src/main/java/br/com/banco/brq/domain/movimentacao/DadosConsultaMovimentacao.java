package br.com.banco.brq.domain.movimentacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosConsultaMovimentacao {
    private LocalDate data;
    private Long idCliente;
}
