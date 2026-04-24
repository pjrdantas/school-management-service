package br.com.escola.enrollment.domain.exception;

public class MatriculaAlunoNaoEncontradoException extends RuntimeException {

    public MatriculaAlunoNaoEncontradoException(Long alunoId) {
        super("Aluno não encontrado para o id " + alunoId);
    }
}
