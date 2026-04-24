package br.com.escola.academiccatalog.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.academiccatalog.application.usecase.BuscarTurmaPorIdUseCase;
import br.com.escola.academiccatalog.application.usecase.CriarTurmaUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    private final CriarTurmaUseCase criarTurmaUseCase;
    private final BuscarTurmaPorIdUseCase buscarTurmaPorIdUseCase;

    public TurmaController(CriarTurmaUseCase criarTurmaUseCase, BuscarTurmaPorIdUseCase buscarTurmaPorIdUseCase) {
        this.criarTurmaUseCase = criarTurmaUseCase;
        this.buscarTurmaPorIdUseCase = buscarTurmaPorIdUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TurmaResponse criar(@Valid @RequestBody TurmaRequest request) {
        return criarTurmaUseCase.executar(request);
    }

    @GetMapping("/{id}")
    public TurmaResponse buscarPorId(@PathVariable Long id) {
        return buscarTurmaPorIdUseCase.executar(id);
    }
}
