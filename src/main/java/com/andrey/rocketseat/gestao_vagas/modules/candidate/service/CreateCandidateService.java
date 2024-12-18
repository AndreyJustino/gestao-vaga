package com.andrey.rocketseat.gestao_vagas.modules.candidate.service;

import com.andrey.rocketseat.gestao_vagas.exceptions.CompanyOrUserFoundException;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.CandidateEntity;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateService {
    @Autowired //dizendo que o spring sera o responsavel por gerenciar a instaciacao dessa classe
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate){
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((value) -> {
                    throw new CompanyOrUserFoundException("Usuario ja cadastrado!");
                });

        return this.candidateRepository.save(candidate);
    }
}
