package com.andrey.rocketseat.gestao_vagas.modules.company.service;

import com.andrey.rocketseat.gestao_vagas.modules.company.entities.JobsEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobService {

    @Autowired
    private JobRepository jobRepository;

    public JobsEntity createJobService(JobsEntity jobsEntity){
        return this.jobRepository.save(jobsEntity);
    }
}
