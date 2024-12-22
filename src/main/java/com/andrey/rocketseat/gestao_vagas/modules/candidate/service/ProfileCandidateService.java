package com.andrey.rocketseat.gestao_vagas.modules.candidate.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.andrey.rocketseat.gestao_vagas.modules.candidate.CandidateEntity;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.dto.CandidateProfileDTO;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class ProfileCandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    /*
        @Autowired
        private CandidateProfileDTO candidateProfileDTO;
     */

    public CandidateProfileDTO execute(UUID idCandidate){
        CandidateEntity candidateEntity = candidateRepository.findById(idCandidate).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Usuario não encontrado");
            }
        );

        /*
            candidateProfileDTO.setName(candidateEntity.getName());
            candidateProfileDTO.setUsername(candidateEntity.getUsername());
            candidateProfileDTO.setEmail(candidateEntity.getEmail());
            candidateProfileDTO.setDescription(candidateEntity.getDescription());
            candidateProfileDTO.setCurriculum(candidateEntity.getCurriculum());
        */

        CandidateProfileDTO candidateProfileDTO = CandidateProfileDTO.builder()
            .email(candidateEntity.getEmail())
            .curriculum(candidateEntity.getCurriculum())
            .username(candidateEntity.getUsername())
            .name(candidateEntity.getName())
            .description(candidateEntity.getDescription())
        .build();

            System.out.println("===== Candidate Service Profile ====");

        return candidateProfileDTO;
    }
}