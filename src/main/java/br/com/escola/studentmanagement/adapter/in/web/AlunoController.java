package br.com.escola.studentmanagement.adapter.in.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.studentmanagement.application.usecase.CriarAlunoUseCase;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final CriarAlunoUseCase criarAlunoUseCase;

    public AlunoController(CriarAlunoUseCase criarAlunoUseCase) {
        this.criarAlunoUseCase = criarAlunoUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponse criar(@Valid @RequestBody AlunoRequest request) {
        return criarAlunoUseCase.executar(request);
    }
}
