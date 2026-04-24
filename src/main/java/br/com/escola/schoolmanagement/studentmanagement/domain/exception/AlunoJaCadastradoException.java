package br.com.escola.schoolmanagement.studentmanagement.domain.exception;

public class AlunoJaCadastradoException extends RuntimeException {

    public AlunoJaCadastradoException() {
        super("Já existe aluno cadastrado com este CPF");
    }
}
