package br.com.escola.academiccatalog.application.usecase;

import org.springframework.stereotype.Service;

import br.com.escola.academiccatalog.adapter.in.web.TurmaRequest;
import br.com.escola.academiccatalog.adapter.in.web.TurmaResponse;
import br.com.escola.academiccatalog.adapter.out.persistence.entity.PeriodoLetivoEntity;
import br.com.escola.academiccatalog.adapter.out.persistence.entity.TurmaEntity;
import br.com.escola.academiccatalog.adapter.out.persistence.repository.PeriodoLetivoJpaRepository;
import br.com.escola.academiccatalog.adapter.out.persistence.repository.TurmaJpaRepository;
import br.com.escola.academiccatalog.domain.exception.PeriodoLetivoNaoEncontradoException;
import br.com.escola.academiccatalog.domain.exception.TurmaJaCadastradaException;

@Service
public class CriarTurmaUseCase {

    private final TurmaJpaRepository turmaJpaRepository;
    private final PeriodoLetivoJpaRepository periodoLetivoJpaRepository;

    public CriarTurmaUseCase(TurmaJpaRepository turmaJpaRepository, PeriodoLetivoJpaRepository periodoLetivoJpaRepository) {
        this.turmaJpaRepository = turmaJpaRepository;
        this.periodoLetivoJpaRepository = periodoLetivoJpaRepository;
    }

    public TurmaResponse executar(TurmaRequest request) {
        PeriodoLetivoEntity periodoLetivo = periodoLetivoJpaRepository.findById(request.periodoLetivoId())
                .orElseThrow(() -> new PeriodoLetivoNaoEncontradoException(request.periodoLetivoId()));

        turmaJpaRepository.findByCodigoAndPeriodoLetivoId(request.codigo(), request.periodoLetivoId())
                .ifPresent(turma -> {
                    throw new TurmaJaCadastradaException(request.codigo(), request.periodoLetivoId());
                });

        TurmaEntity turmaEntity = new TurmaEntity();
        turmaEntity.setCodigo(request.codigo());
        turmaEntity.setNome(request.nome());
        turmaEntity.setCapacidade(request.capacidade());
        turmaEntity.setPeriodoLetivo(periodoLetivo);

        TurmaEntity persisted = turmaJpaRepository.save(turmaEntity);

        return new TurmaResponse(
                persisted.getId(),
                persisted.getCodigo(),
                persisted.getNome(),
                persisted.getCapacidade(),
                persisted.getPeriodoLetivo().getId(),
                persisted.getCreatedAt());
    }
}
