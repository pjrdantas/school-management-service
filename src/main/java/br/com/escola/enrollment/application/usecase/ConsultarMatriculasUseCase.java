package br.com.escola.enrollment.application.usecase;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.escola.enrollment.adapter.in.web.MatriculaResponse;
import br.com.escola.enrollment.adapter.out.persistence.entity.MatriculaEntity;
import br.com.escola.enrollment.adapter.out.persistence.repository.MatriculaJpaRepository;
import br.com.escola.enrollment.domain.MatriculaStatus;
import br.com.escola.enrollment.domain.exception.MatriculaStatusInvalidoException;

@Service
public class ConsultarMatriculasUseCase {

    private final MatriculaJpaRepository matriculaJpaRepository;

    public ConsultarMatriculasUseCase(MatriculaJpaRepository matriculaJpaRepository) {
        this.matriculaJpaRepository = matriculaJpaRepository;
    }

    public List<MatriculaResponse> executar(Long alunoId, Long turmaId, Long periodoLetivoId, String status) {
        MatriculaStatus matriculaStatus = parseStatus(status);

        Specification<MatriculaEntity> spec = (root, query, cb) -> cb.conjunction();

        if (alunoId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("aluno").get("id"), alunoId));
        }

        if (turmaId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("turma").get("id"), turmaId));
        }

        if (periodoLetivoId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("periodoLetivo").get("id"), periodoLetivoId));
        }

        if (matriculaStatus != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), matriculaStatus));
        }

        return matriculaJpaRepository.findAll(spec).stream()
                .map(entity -> new MatriculaResponse(
                        entity.getId(),
                        entity.getAluno().getId(),
                        entity.getTurma().getId(),
                        entity.getPeriodoLetivo().getId(),
                        entity.getStatus().name(),
                        entity.getCreatedAt()))
                .toList();
    }

    private MatriculaStatus parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return null;
        }

        try {
            return MatriculaStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new MatriculaStatusInvalidoException(status);
        }
    }
}
