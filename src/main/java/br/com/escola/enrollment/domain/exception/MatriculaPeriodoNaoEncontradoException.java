package br.com.escola.enrollment.domain.exception;

public class MatriculaPeriodoNaoEncontradoException extends RuntimeException {

    public MatriculaPeriodoNaoEncontradoException(Long periodoLetivoId) {
        super("Período letivo não encontrado para o id " + periodoLetivoId);
    }
}
