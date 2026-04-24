package br.com.escola.schoolmanagement.studentmanagement.adapter.out.persistence.repository;

import br.com.escola.schoolmanagement.studentmanagement.adapter.out.persistence.entity.AlunoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoJpaRepository extends JpaRepository<AlunoEntity, Long> {

    Optional<AlunoEntity> findByCpf(String cpf);
}
