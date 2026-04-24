package br.com.escola.shared.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.escola.enrollment.adapter.in.web.MatriculaController;
import br.com.escola.enrollment.domain.exception.MatriculaAlunoNaoEncontradoException;
import br.com.escola.enrollment.domain.exception.MatriculaPeriodoNaoEncontradoException;
import br.com.escola.enrollment.domain.exception.MatriculaTurmaNaoEncontradaException;
import br.com.escola.enrollment.domain.exception.TurmaPeriodoInconsistenteException;

@RestControllerAdvice(basePackageClasses = MatriculaController.class)
public class EnrollmentApiExceptionHandler {

    @ExceptionHandler(MatriculaAlunoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleAlunoNotFound(MatriculaAlunoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(MatriculaTurmaNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> handleTurmaNotFound(MatriculaTurmaNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(MatriculaPeriodoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handlePeriodoNotFound(MatriculaPeriodoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(TurmaPeriodoInconsistenteException.class)
    public ResponseEntity<Map<String, String>> handleInconsistencia(TurmaPeriodoInconsistenteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
    }
}
