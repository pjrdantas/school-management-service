package br.com.escola.studentmanagement.application.usecase;

import org.springframework.stereotype.Service;

import br.com.escola.studentmanagement.adapter.in.web.AlunoRequest;
import br.com.escola.studentmanagement.adapter.in.web.AlunoResponse;
import br.com.escola.studentmanagement.adapter.out.persistence.entity.AlunoEntity;
import br.com.escola.studentmanagement.adapter.out.persistence.repository.AlunoJpaRepository;
import br.com.escola.studentmanagement.domain.exception.AlunoJaCadastradoException;

@Service
public class CriarAlunoUseCase {

    private final AlunoJpaRepository alunoJpaRepository;

    public CriarAlunoUseCase(AlunoJpaRepository alunoJpaRepository) {
        this.alunoJpaRepository = alunoJpaRepository;
    }

    public AlunoResponse executar(AlunoRequest request) {
        if (alunoJpaRepository.findByCpf(request.cpf()).isPresent()) {
            throw new AlunoJaCadastradoException();
        }

        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setNomeCompleto(request.nomeCompleto());
        alunoEntity.setCpf(request.cpf());
        alunoEntity.setEmail(request.email());
        alunoEntity.setDataNascimento(request.dataNascimento());

        AlunoEntity persisted = alunoJpaRepository.save(alunoEntity);

        return new AlunoResponse(
                persisted.getId(),
                persisted.getNomeCompleto(),
                persisted.getCpf(),
                persisted.getEmail(),
                persisted.getDataNascimento(),
                persisted.getCreatedAt());

    }
}
