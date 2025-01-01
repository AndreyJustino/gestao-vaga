package com.andrey.rocketseat.gestao_vagas.modules.company.service;

import com.andrey.rocketseat.gestao_vagas.exceptions.CompanyNotFoundException;
import com.andrey.rocketseat.gestao_vagas.modules.company.entities.JobsEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.repository.CompanyRepository;
import com.andrey.rocketseat.gestao_vagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobsEntity createJobService(JobsEntity jobsEntity){
        this.companyRepository.findById(jobsEntity.getId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });

        return this.jobRepository.save(jobsEntity);
    }
}
