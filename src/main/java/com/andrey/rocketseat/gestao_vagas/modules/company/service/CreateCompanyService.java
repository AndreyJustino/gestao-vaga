package com.andrey.rocketseat.gestao_vagas.modules.company.service;

import com.andrey.rocketseat.gestao_vagas.exceptions.CompanyFoundException;
import com.andrey.rocketseat.gestao_vagas.modules.company.CompanyEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity createCompany(CompanyEntity company){
        this.companyRepository.findByCnpjOrUsername(company.getCnpj(), company.getUsername())
                .ifPresent((value) -> {
                    throw new CompanyFoundException();
                });

        return this.companyRepository.save(company);
    }
}
