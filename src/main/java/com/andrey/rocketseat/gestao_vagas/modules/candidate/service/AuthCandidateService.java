package com.andrey.rocketseat.gestao_vagas.modules.candidate.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.andrey.rocketseat.gestao_vagas.modules.candidate.CandidateEntity;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.andrey.rocketseat.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.security.auth.message.AuthException;

@Service
public class AuthCandidateService {

    @Value("%{security.token.secret}")
    private String secretKey;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CandidateRepository candidateRepository;

    public AuthCandidateResponseDTO execute(AuthCandidateDTO authCandidateDTO) throws AuthException {

        CandidateEntity candidateEntity = this.candidateRepository.findByEmail(authCandidateDTO.getEmail())
        .orElseThrow(
            () -> {
                throw new IllegalArgumentException("Email não encontrado");
            }
        );

        boolean password = passwordEncoder.matches(authCandidateDTO.getPassword(), candidateEntity.getPassword());

        if(!password){
            throw new AuthException("Senha invalida!");
        }

        Instant expiteIn = Instant.now().plus(Duration.ofHours(4));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token = JWT.create().withIssuer("QuemAssinaIsso?")
            .withExpiresAt(expiteIn)
            .withSubject(candidateEntity.getId().toString())
            .withClaim("roles", Arrays.asList("candidate"))
            .sign(algorithm);

        
        
        AuthCandidateResponseDTO authCandidateResponseDTO = AuthCandidateResponseDTO.builder()
            .acess_token(token)
            .expires_in(expiteIn)
            .build();
        
        return authCandidateResponseDTO;
    }
}
