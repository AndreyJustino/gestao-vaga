package com.andrey.rocketseat.gestao_vagas.modules.company.repository;

import com.andrey.rocketseat.gestao_vagas.modules.company.entities.JobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobsEntity, UUID> {
}
