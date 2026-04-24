package br.com.escola.shared.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.escola.academiccatalog.adapter.in.web.PeriodoLetivoController;
import br.com.escola.academiccatalog.domain.exception.PeriodoLetivoInvalidoException;
import br.com.escola.academiccatalog.domain.exception.PeriodoLetivoNaoEncontradoException;
import br.com.escola.academiccatalog.domain.exception.TurmaJaCadastradaException;
import br.com.escola.academiccatalog.domain.exception.TurmaNaoEncontradaException;

@RestControllerAdvice(basePackageClasses = PeriodoLetivoController.class)
public class AcademicCatalogApiExceptionHandler {

    @ExceptionHandler(PeriodoLetivoInvalidoException.class)
    public ResponseEntity<Map<String, String>> handlePeriodoInvalido(PeriodoLetivoInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(PeriodoLetivoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handlePeriodoNotFound(PeriodoLetivoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(TurmaNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> handleTurmaNotFound(TurmaNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(TurmaJaCadastradaException.class)
    public ResponseEntity<Map<String, String>> handleTurmaDuplicada(TurmaJaCadastradaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", ex.getMessage()));
    }
}
