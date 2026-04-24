package br.com.escola.academiccatalog.domain.exception;

public class PeriodoLetivoNaoEncontradoException extends RuntimeException {

    public PeriodoLetivoNaoEncontradoException(Long id) {
        super("Período letivo não encontrado para o id " + id);
    }
}
