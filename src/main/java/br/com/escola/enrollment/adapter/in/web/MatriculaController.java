package br.com.escola.enrollment.adapter.in.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.enrollment.application.usecase.ConsultarMatriculasUseCase;
import br.com.escola.enrollment.application.usecase.CriarMatriculaUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    private final CriarMatriculaUseCase criarMatriculaUseCase;
    private final ConsultarMatriculasUseCase consultarMatriculasUseCase;

    public MatriculaController(
            CriarMatriculaUseCase criarMatriculaUseCase,
            ConsultarMatriculasUseCase consultarMatriculasUseCase) {
        this.criarMatriculaUseCase = criarMatriculaUseCase;
        this.consultarMatriculasUseCase = consultarMatriculasUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponse criar(@Valid @RequestBody MatriculaRequest request) {
        return criarMatriculaUseCase.executar(request);
    }

    @GetMapping
    public List<MatriculaResponse> consultar(
            @RequestParam(required = false) Long alunoId,
            @RequestParam(required = false) Long turmaId,
            @RequestParam(required = false) Long periodoLetivoId,
            @RequestParam(required = false) String status) {
        return consultarMatriculasUseCase.executar(alunoId, turmaId, periodoLetivoId, status);
    }
}