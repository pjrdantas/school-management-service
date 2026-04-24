package br.com.escola.studentmanagement.domain.exception;

public class AlunoNaoEncontradoException extends RuntimeException {

    public AlunoNaoEncontradoException(Long id) {
        super("Aluno não encontrado para o id " + id);
    }
}
