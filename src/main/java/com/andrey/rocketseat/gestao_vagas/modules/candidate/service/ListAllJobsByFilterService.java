package com.andrey.rocketseat.gestao_vagas.modules.candidate.service;

import com.andrey.rocketseat.gestao_vagas.modules.company.entities.JobsEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterService {

    @Autowired
    private JobRepository jobRepository;

    public List<JobsEntity> execute(String filter){
        return this.jobRepository.findByDescriptionContaining(filter);
    }
}
