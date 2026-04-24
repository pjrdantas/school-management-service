package br.com.escola.enrollment.domain.exception;

public class MatriculaTurmaNaoEncontradaException extends RuntimeException {

    public MatriculaTurmaNaoEncontradaException(Long turmaId) {
        super("Turma não encontrada para o id " + turmaId);
    }
}
