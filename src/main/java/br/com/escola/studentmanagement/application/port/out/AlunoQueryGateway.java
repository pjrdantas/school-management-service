package br.com.escola.studentmanagement.application.port.out;

import java.util.Optional;

import br.com.escola.studentmanagement.application.dto.AlunoOutput;

public interface AlunoQueryGateway {

    Optional<AlunoOutput> findById(Long id);

    boolean existsById(Long id);
}
