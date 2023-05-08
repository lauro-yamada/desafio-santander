package br.com.banco.brq.controller;

import br.com.banco.brq.domain.cliente.ClienteRepository;
import br.com.banco.brq.domain.cliente.DadosCadastroCliente;
import br.com.banco.brq.domain.conta.DadosCadastroConta;
import br.com.banco.brq.domain.movimentacao.DadosMovimentacao;
import br.com.banco.brq.domain.movimentacao.TipoMovimentacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroCliente> dadosCadastroClienteJson;

    @Autowired
    private JacksonTester<DadosMovimentacao> dadosMovimentacaoson;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    public void cadastrarClienteCenario1() throws Exception {
        var response = mvc.perform(post("/cliente")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes estao válidas")
    public void cadastrarClienteCenario2() throws Exception {
        var response = mvc
                .perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroClienteJson.write(
                                new DadosCadastroCliente("Joao Jose", LocalDate.of(2000, 10, 11),
                                        new DadosCadastroConta("34566", new BigDecimal("1000"), true))
        ).getJson())).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes estao válidas")
    public void cadastrarClienteCenario3() throws Exception {
        var response = mvc
                .perform(post("/cliente/sacar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosMovimentacaoson.write(
                                new DadosMovimentacao(TipoMovimentacao.SAQUE, new BigDecimal("100"), 1L)
                        ).getJson())).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    public void cadastrarClienteCenario4() throws Exception {
        var response = mvc.perform(post("/cliente/sacar")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


}