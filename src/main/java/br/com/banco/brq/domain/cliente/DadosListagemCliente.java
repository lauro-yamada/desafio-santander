package br.com.banco.brq.domain.cliente;

import br.com.banco.brq.domain.conta.DadosListagemConta;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosListagemCliente {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private DadosListagemConta conta;

    public DadosListagemCliente(Cliente dados) {
        this.id = dados.getId();
        this.nome = dados.getNome();
        this.dataNascimento = dados.getDataNascimento();
        this.conta = new DadosListagemConta(dados.getConta());
    }
}
