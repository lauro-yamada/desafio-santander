package br.com.banco.brq.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosErroValidacao {

    private String campo;
    private String mensagem;

    public DadosErroValidacao(FieldError fieldError) {
        this.campo = fieldError.getField();
        this.mensagem = fieldError.getDefaultMessage();
    }
}
