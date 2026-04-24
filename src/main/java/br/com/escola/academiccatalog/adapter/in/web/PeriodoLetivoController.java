package br.com.escola.academiccatalog.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.academiccatalog.application.usecase.BuscarPeriodoLetivoPorIdUseCase;
import br.com.escola.academiccatalog.application.usecase.CriarPeriodoLetivoUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/periodos-letivos")
public class PeriodoLetivoController {

    private final CriarPeriodoLetivoUseCase criarPeriodoLetivoUseCase;
    private final BuscarPeriodoLetivoPorIdUseCase buscarPeriodoLetivoPorIdUseCase;

    public PeriodoLetivoController(
            CriarPeriodoLetivoUseCase criarPeriodoLetivoUseCase,
            BuscarPeriodoLetivoPorIdUseCase buscarPeriodoLetivoPorIdUseCase) {
        this.criarPeriodoLetivoUseCase = criarPeriodoLetivoUseCase;
        this.buscarPeriodoLetivoPorIdUseCase = buscarPeriodoLetivoPorIdUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PeriodoLetivoResponse criar(@Valid @RequestBody PeriodoLetivoRequest request) {
        return criarPeriodoLetivoUseCase.executar(request);
    }

    @GetMapping("/{id}")
    public PeriodoLetivoResponse buscarPorId(@PathVariable Long id) {
        return buscarPeriodoLetivoPorIdUseCase.executar(id);
    }
}
