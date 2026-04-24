package br.com.escola.enrollment.application.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.escola.academiccatalog.adapter.out.persistence.entity.PeriodoLetivoEntity;
import br.com.escola.academiccatalog.adapter.out.persistence.entity.TurmaEntity;
import br.com.escola.academiccatalog.adapter.out.persistence.repository.PeriodoLetivoJpaRepository;
import br.com.escola.academiccatalog.adapter.out.persistence.repository.TurmaJpaRepository;
import br.com.escola.enrollment.adapter.in.web.MatriculaRequest;
import br.com.escola.enrollment.adapter.in.web.MatriculaResponse;
import br.com.escola.enrollment.adapter.out.persistence.entity.MatriculaEntity;
import br.com.escola.enrollment.adapter.out.persistence.repository.MatriculaJpaRepository;
import br.com.escola.enrollment.domain.MatriculaStatus;
import br.com.escola.enrollment.domain.exception.MatriculaAlunoNaoEncontradoException;
import br.com.escola.enrollment.domain.exception.MatriculaPeriodoNaoEncontradoException;
import br.com.escola.enrollment.domain.exception.MatriculaTurmaNaoEncontradaException;
import br.com.escola.enrollment.domain.exception.TurmaPeriodoInconsistenteException;
import br.com.escola.studentmanagement.adapter.out.persistence.entity.AlunoEntity;
import br.com.escola.studentmanagement.adapter.out.persistence.repository.AlunoJpaRepository;

@Service
public class CriarMatriculaUseCase {

    private final MatriculaJpaRepository matriculaJpaRepository;
    private final AlunoJpaRepository alunoJpaRepository;
    private final TurmaJpaRepository turmaJpaRepository;
    private final PeriodoLetivoJpaRepository periodoLetivoJpaRepository;

    public CriarMatriculaUseCase(
            MatriculaJpaRepository matriculaJpaRepository,
            AlunoJpaRepository alunoJpaRepository,
            TurmaJpaRepository turmaJpaRepository,
            PeriodoLetivoJpaRepository periodoLetivoJpaRepository) {
        this.matriculaJpaRepository = matriculaJpaRepository;
        this.alunoJpaRepository = alunoJpaRepository;
        this.turmaJpaRepository = turmaJpaRepository;
        this.periodoLetivoJpaRepository = periodoLetivoJpaRepository;
    }

    public MatriculaResponse executar(MatriculaRequest request) {
        AlunoEntity aluno = alunoJpaRepository.findById(request.alunoId())
                .orElseThrow(() -> new MatriculaAlunoNaoEncontradoException(request.alunoId()));

        TurmaEntity turma = turmaJpaRepository.findById(request.turmaId())
                .orElseThrow(() -> new MatriculaTurmaNaoEncontradaException(request.turmaId()));

        PeriodoLetivoEntity periodoLetivo = periodoLetivoJpaRepository.findById(request.periodoLetivoId())
                .orElseThrow(() -> new MatriculaPeriodoNaoEncontradoException(request.periodoLetivoId()));

        if (!turma.getPeriodoLetivo().getId().equals(periodoLetivo.getId())) {
            throw new TurmaPeriodoInconsistenteException(turma.getId(), periodoLetivo.getId());
        }

        MatriculaEntity matriculaEntity = new MatriculaEntity();
        matriculaEntity.setAluno(aluno);
        matriculaEntity.setTurma(turma);
        matriculaEntity.setPeriodoLetivo(periodoLetivo);
        matriculaEntity.setStatus(MatriculaStatus.ATIVA);
        matriculaEntity.setCreatedAt(LocalDateTime.now());

        MatriculaEntity persisted = matriculaJpaRepository.save(matriculaEntity);

        return new MatriculaResponse(
                persisted.getId(),
                persisted.getAluno().getId(),
                persisted.getTurma().getId(),
                persisted.getPeriodoLetivo().getId(),
                persisted.getStatus().name(),
                persisted.getCreatedAt());
    }
}
