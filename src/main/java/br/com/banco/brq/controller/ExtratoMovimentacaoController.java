package br.com.banco.brq.controller;

import br.com.banco.brq.domain.cliente.ClienteRepository;
import br.com.banco.brq.domain.movimentacao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/extrato")
public class ExtratoMovimentacaoController {

    @Autowired
    private ClienteRepository clienteREpository;

    @Autowired
    private MovimentacaoRepository movimntacaoRepository;

    @GetMapping(path = "/listar")
    public ResponseEntity<Page<DadosListagemMovimentacao>> listar(@PageableDefault(size=10, sort = {"data"}) Pageable paginacao, @RequestBody DadosConsultaMovimentacao dados){
        var  dataIni = dados.getData().atTime(0, 0);
        var  dataFim = dados.getData().atTime(23, 59);
        var page = movimntacaoRepository.findMovimentacaByData(dataIni, dataFim, paginacao).map(DadosListagemMovimentacao::new);

        return ResponseEntity.ok(page);

    }

    @GetMapping("/detalhar/{idMovimentacao}")
    public ResponseEntity<DadosDetalhamentoMovimentacao> detalhar(@PathVariable Long idMovimentacao){

        var movimento = movimntacaoRepository.findById(idMovimentacao);

        return ResponseEntity.ok(new DadosDetalhamentoMovimentacao(movimento.get()));

    }

}
