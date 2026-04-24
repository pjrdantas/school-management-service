package br.com.escola.academiccatalog.application.usecase;

import org.springframework.stereotype.Service;

import br.com.escola.academiccatalog.adapter.in.web.TurmaResponse;
import br.com.escola.academiccatalog.adapter.out.persistence.entity.TurmaEntity;
import br.com.escola.academiccatalog.adapter.out.persistence.repository.TurmaJpaRepository;
import br.com.escola.academiccatalog.domain.exception.TurmaNaoEncontradaException;

@Service
public class BuscarTurmaPorIdUseCase {

    private final TurmaJpaRepository turmaJpaRepository;

    public BuscarTurmaPorIdUseCase(TurmaJpaRepository turmaJpaRepository) {
        this.turmaJpaRepository = turmaJpaRepository;
    }

    public TurmaResponse executar(Long id) {
        TurmaEntity entity = turmaJpaRepository.findById(id)
                .orElseThrow(() -> new TurmaNaoEncontradaException(id));

        return new TurmaResponse(
                entity.getId(),
                entity.getCodigo(),
                entity.getNome(),
                entity.getCapacidade(),
                entity.getPeriodoLetivo().getId(),
                entity.getCreatedAt());
    }
}
