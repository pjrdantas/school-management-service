package br.com.escola.enrollment.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.escola.academiccatalog.adapter.out.persistence.repository.TurmaJpaRepository;
import br.com.escola.enrollment.application.port.out.TurmaConsultaGateway;

@Component
public class TurmaConsultaPersistenceGateway implements TurmaConsultaGateway {

    private final TurmaJpaRepository turmaJpaRepository;

    public TurmaConsultaPersistenceGateway(TurmaJpaRepository turmaJpaRepository) {
        this.turmaJpaRepository = turmaJpaRepository;
    }

    @Override
    public boolean existsById(Long id) {
        return turmaJpaRepository.existsById(id);
    }

    @Override
    public Optional<Long> findPeriodoLetivoIdByTurmaId(Long turmaId) {
        return turmaJpaRepository.findById(turmaId).map(turma -> turma.getPeriodoLetivo().getId());
    }
}
