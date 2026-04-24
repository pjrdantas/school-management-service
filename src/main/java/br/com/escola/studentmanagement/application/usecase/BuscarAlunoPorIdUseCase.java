package br.com.escola.studentmanagement.application.usecase;

import org.springframework.stereotype.Service;

import br.com.escola.studentmanagement.adapter.in.web.AlunoResponse;
import br.com.escola.studentmanagement.adapter.out.persistence.entity.AlunoEntity;
import br.com.escola.studentmanagement.adapter.out.persistence.repository.AlunoJpaRepository;
import br.com.escola.studentmanagement.domain.exception.AlunoNaoEncontradoException;

@Service
public class BuscarAlunoPorIdUseCase {

    private final AlunoJpaRepository alunoJpaRepository;

    public BuscarAlunoPorIdUseCase(AlunoJpaRepository alunoJpaRepository) {
        this.alunoJpaRepository = alunoJpaRepository;
    }

    public AlunoResponse executar(Long id) {
        AlunoEntity alunoEntity = alunoJpaRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));

        return new AlunoResponse(
                alunoEntity.getId(),
                alunoEntity.getNomeCompleto(),
                alunoEntity.getCpf(),
                alunoEntity.getEmail(),
                alunoEntity.getDataNascimento(),
                alunoEntity.getCreatedAt());
    }
}
