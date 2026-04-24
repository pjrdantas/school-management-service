package br.com.escola.enrollment.domain.exception;

public class MatriculaStatusInvalidoException extends RuntimeException {

    public MatriculaStatusInvalidoException(String status) {
        super("Status de matrícula inválido: " + status);
    }
}
