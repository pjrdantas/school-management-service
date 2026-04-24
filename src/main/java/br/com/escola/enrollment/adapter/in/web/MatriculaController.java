package br.com.escola.enrollment.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.enrollment.application.usecase.CriarMatriculaUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    private final CriarMatriculaUseCase criarMatriculaUseCase;

    public MatriculaController(CriarMatriculaUseCase criarMatriculaUseCase) {
        this.criarMatriculaUseCase = criarMatriculaUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponse criar(@Valid @RequestBody MatriculaRequest request) {
        return criarMatriculaUseCase.executar(request);
    }
}
