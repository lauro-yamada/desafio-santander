package br.com.banco.brq.controller;

import br.com.banco.brq.domain.cliente.*;
import br.com.banco.brq.domain.movimentacao.DadosDetalhamentoMovimentacao;
import br.com.banco.brq.domain.movimentacao.DadosMovimentacao;
import br.com.banco.brq.service.movimentacao.DepositoService;
import br.com.banco.brq.service.movimentacao.SaqueService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private DepositoService depositooService;

    @Autowired
    private SaqueService saqueService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder){
       var cliente = new Cliente(dados);
        repository.save(cliente);
        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>>  listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        var page =  repository.findAll(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
         var cliente = repository.getReferenceById(id);
         return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @PostMapping(path = "/sacar")
    @Transactional
    public ResponseEntity sacar(@RequestBody @Valid DadosMovimentacao dados, UriComponentsBuilder uriBuilder){

        var movimentacao = saqueService.sacar(dados);
        var uri = uriBuilder.path("/extrato/{idMovimentacao}").buildAndExpand(movimentacao.getIdMovimentacao()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMovimentacao(movimentacao));

    }

    @PostMapping(path = "/depositar")
    @Transactional
    public ResponseEntity depositar(@RequestBody @Valid DadosMovimentacao dados, UriComponentsBuilder uriBuilder){

        var movimentacao = depositooService.depositar(dados);
        var uri = uriBuilder.path("/extrato/{idMovimentacao}").buildAndExpand(movimentacao.getIdMovimentacao()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMovimentacao(movimentacao));

    }

}
