package br.com.banco.brq.domain.movimentacao;

import br.com.banco.brq.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name="movimentacao")
@Entity(name="Movimentacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimentacao;
    private TipoMovimentacao tipo;
    private BigDecimal valor;
    private LocalDateTime data;
    private BigDecimal valorTaxaAdm;
    @ManyToOne
    @JoinColumn(name="idCliente", nullable=false)
    private Cliente cliente;

    public Movimentacao(Cliente cliente, DadosMovimentacao dados, BigDecimal valorTaxaAdm) {
        this.tipo = dados.getTipo();
        this.valor = dados.getValor();
        this.data = LocalDateTime.now();
        this.valorTaxaAdm = valorTaxaAdm;
        this.cliente = cliente;
    }
}
