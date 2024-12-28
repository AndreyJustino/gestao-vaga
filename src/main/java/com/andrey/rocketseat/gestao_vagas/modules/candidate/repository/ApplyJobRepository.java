package com.andrey.rocketseat.gestao_vagas.modules.candidate.repository;

import com.andrey.rocketseat.gestao_vagas.modules.candidate.entity.ApplyJobCandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobCandidateEntity, UUID> {
}
