package com.andrey.rocketseat.gestao_vagas.modules.company.service;

import com.andrey.rocketseat.gestao_vagas.exceptions.CompanyOrUserFoundException;
import com.andrey.rocketseat.gestao_vagas.modules.company.entities.CompanyEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity createCompany(CompanyEntity company){
        this.companyRepository.findByCnpjOrUsernameOrEmail(company.getCnpj(), company.getUsername(), company.getEmail())
                .ifPresent((value) -> {
                    throw new CompanyOrUserFoundException("Companhia ja cadastrada!");
                });

        return this.companyRepository.save(company);
    }
}
