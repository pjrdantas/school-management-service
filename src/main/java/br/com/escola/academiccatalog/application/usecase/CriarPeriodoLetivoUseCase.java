package br.com.escola.academiccatalog.application.usecase;

import org.springframework.stereotype.Service;

import br.com.escola.academiccatalog.adapter.in.web.PeriodoLetivoRequest;
import br.com.escola.academiccatalog.adapter.in.web.PeriodoLetivoResponse;
import br.com.escola.academiccatalog.adapter.out.persistence.entity.PeriodoLetivoEntity;
import br.com.escola.academiccatalog.adapter.out.persistence.repository.PeriodoLetivoJpaRepository;
import br.com.escola.academiccatalog.domain.exception.PeriodoLetivoInvalidoException;

@Service
public class CriarPeriodoLetivoUseCase {

    private final PeriodoLetivoJpaRepository periodoLetivoJpaRepository;

    public CriarPeriodoLetivoUseCase(PeriodoLetivoJpaRepository periodoLetivoJpaRepository) {
        this.periodoLetivoJpaRepository = periodoLetivoJpaRepository;
    }

    public PeriodoLetivoResponse executar(PeriodoLetivoRequest request) {
        if (request.dataFim().isBefore(request.dataInicio())) {
            throw new PeriodoLetivoInvalidoException();
        }

        PeriodoLetivoEntity periodoLetivoEntity = new PeriodoLetivoEntity();
        periodoLetivoEntity.setNome(request.nome());
        periodoLetivoEntity.setDataInicio(request.dataInicio());
        periodoLetivoEntity.setDataFim(request.dataFim());

        PeriodoLetivoEntity persisted = periodoLetivoJpaRepository.save(periodoLetivoEntity);

        return new PeriodoLetivoResponse(
                persisted.getId(),
                persisted.getNome(),
                persisted.getDataInicio(),
                persisted.getDataFim(),
                persisted.getCreatedAt());
    }
}
