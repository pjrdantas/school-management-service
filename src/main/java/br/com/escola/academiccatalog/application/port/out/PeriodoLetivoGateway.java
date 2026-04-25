package br.com.escola.academiccatalog.application.port.out;

import java.util.Optional;

import br.com.escola.academiccatalog.application.dto.PeriodoLetivoInput;
import br.com.escola.academiccatalog.application.dto.PeriodoLetivoOutput;

public interface PeriodoLetivoGateway {

    Optional<PeriodoLetivoOutput> findById(Long id);

    boolean existsById(Long id);

    PeriodoLetivoOutput save(PeriodoLetivoInput input);
}
