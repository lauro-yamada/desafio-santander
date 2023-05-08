package br.com.banco.brq.service.movimentacao;

import br.com.banco.brq.domain.cliente.ClienteRepository;
import br.com.banco.brq.domain.movimentacao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculaTaxaAdministrativaService {

    @Autowired
    private ClienteRepository clienteRepository;
    public BigDecimal calcular(DadosMovimentacao dados){

        var cliente = clienteRepository.getReferenceById(dados.getIdCliente());
        CobrancaTaxaAdministracao taxaAdministracao =
                new CobrancaTaxaAdministrativaPlanoExclusive(
                        new CobrancaTxaAdministracaoFaixaInicial(
                                new CobrancaTaxaAdministracaoFinal(
                                        new CobrancaSemTaxaAdministracao())));

        return taxaAdministracao.calcular(dados, cliente);

    }

}
