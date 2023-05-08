package br.com.banco.brq.domain.cliente;

import br.com.banco.brq.domain.conta.DadosCadastroConta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DadosCadastroCliente {

    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    @Valid
    private DadosCadastroConta conta;

}
