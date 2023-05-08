package br.com.banco.brq.domain.cliente;

import br.com.banco.brq.domain.conta.Conta;
import br.com.banco.brq.domain.movimentacao.Movimentacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name="cliente")
@Entity(name="Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    @Embedded
    private Conta conta;

    @OneToMany(mappedBy="cliente")
    private Set<Movimentacao> movimentacoes;

    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.getNome();
        this.dataNascimento = dados.getDataNascimento();
        this.conta = new Conta(dados.getConta());
    }
}
