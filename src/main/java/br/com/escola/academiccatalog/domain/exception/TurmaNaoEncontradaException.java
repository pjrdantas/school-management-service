package br.com.escola.academiccatalog.domain.exception;

public class TurmaNaoEncontradaException extends RuntimeException {

    public TurmaNaoEncontradaException(Long id) {
        super("Turma não encontrada para o id " + id);
    }
}
