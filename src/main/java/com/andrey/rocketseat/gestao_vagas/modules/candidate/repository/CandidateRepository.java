package com.andrey.rocketseat.gestao_vagas.modules.candidate.repository;

import com.andrey.rocketseat.gestao_vagas.modules.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// primeiro parametro do jpa: tabela ou entidade que sera gerenciada
// segundo parametro do jpa: é o tipo da chave primaria
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

}
