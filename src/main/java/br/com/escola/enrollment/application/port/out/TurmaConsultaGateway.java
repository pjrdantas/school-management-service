package br.com.escola.enrollment.application.port.out;

import java.util.Optional;

public interface TurmaConsultaGateway {

    boolean existsById(Long id);

    Optional<Long> findPeriodoLetivoIdByTurmaId(Long turmaId);
}
