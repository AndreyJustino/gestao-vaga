package com.andrey.rocketseat.gestao_vagas.modules.candidate.service;

import com.andrey.rocketseat.gestao_vagas.exceptions.CandidateNotFound;
import com.andrey.rocketseat.gestao_vagas.exceptions.CompanyOrUserFoundException;
import com.andrey.rocketseat.gestao_vagas.exceptions.JobNotFound;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.entity.ApplyJobCandidateEntity;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.andrey.rocketseat.gestao_vagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobCandidateEntity execute(UUID idJob, UUID idCandidate){

        this.jobRepository.findById(idJob).orElseThrow(() -> {
            throw new JobNotFound();
        });

        this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new CandidateNotFound();
        });

        this.applyJobRepository.findByCandidateIdAndJobId(idCandidate, idJob).ifPresent(value -> {
            throw new CompanyOrUserFoundException("Candidato ja inscrito na vaga.");
        });

        ApplyJobCandidateEntity applyJobCandidateEntity = ApplyJobCandidateEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        applyJobCandidateEntity = this.applyJobRepository.save(applyJobCandidateEntity);

        return applyJobCandidateEntity;

    }
}
