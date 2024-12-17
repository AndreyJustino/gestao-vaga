package com.andrey.rocketseat.gestao_vagas.modules.company.repository;

import com.andrey.rocketseat.gestao_vagas.modules.company.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByCnpjOrUsername(String cnpj, String username);
}
