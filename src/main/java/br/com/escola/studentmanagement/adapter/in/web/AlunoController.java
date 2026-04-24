package br.com.escola.studentmanagement.adapter.in.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.studentmanagement.application.usecase.BuscarAlunoPorIdUseCase;
import br.com.escola.studentmanagement.application.usecase.CriarAlunoUseCase;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final CriarAlunoUseCase criarAlunoUseCase;
    private final BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase;

    public AlunoController(CriarAlunoUseCase criarAlunoUseCase, BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase) {
        this.criarAlunoUseCase = criarAlunoUseCase;
        this.buscarAlunoPorIdUseCase = buscarAlunoPorIdUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponse criar(@Valid @RequestBody AlunoRequest request) {
        return criarAlunoUseCase.executar(request);
    }

    @GetMapping("/{id}")
    public AlunoResponse buscarPorId(@PathVariable Long id) {
        return buscarAlunoPorIdUseCase.executar(id);
    }
}
