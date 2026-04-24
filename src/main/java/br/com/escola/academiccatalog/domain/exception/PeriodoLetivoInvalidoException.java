package br.com.escola.academiccatalog.domain.exception;

public class PeriodoLetivoInvalidoException extends RuntimeException {

    public PeriodoLetivoInvalidoException() {
        super("dataFim deve ser maior ou igual a dataInicio");
    }
}
