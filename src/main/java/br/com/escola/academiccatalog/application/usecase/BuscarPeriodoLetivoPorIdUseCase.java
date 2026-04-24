package br.com.escola.academiccatalog.application.usecase;

import org.springframework.stereotype.Service;

import br.com.escola.academiccatalog.adapter.in.web.PeriodoLetivoResponse;
import br.com.escola.academiccatalog.adapter.out.persistence.entity.PeriodoLetivoEntity;
import br.com.escola.academiccatalog.adapter.out.persistence.repository.PeriodoLetivoJpaRepository;
import br.com.escola.academiccatalog.domain.exception.PeriodoLetivoNaoEncontradoException;

@Service
public class BuscarPeriodoLetivoPorIdUseCase {

    private final PeriodoLetivoJpaRepository periodoLetivoJpaRepository;

    public BuscarPeriodoLetivoPorIdUseCase(PeriodoLetivoJpaRepository periodoLetivoJpaRepository) {
        this.periodoLetivoJpaRepository = periodoLetivoJpaRepository;
    }

    public PeriodoLetivoResponse executar(Long id) {
        PeriodoLetivoEntity entity = periodoLetivoJpaRepository.findById(id)
                .orElseThrow(() -> new PeriodoLetivoNaoEncontradoException(id));

        return new PeriodoLetivoResponse(
                entity.getId(),
                entity.getNome(),
                entity.getDataInicio(),
                entity.getDataFim(),
                entity.getCreatedAt());
    }
}
