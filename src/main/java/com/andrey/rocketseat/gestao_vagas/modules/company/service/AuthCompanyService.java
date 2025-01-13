package com.andrey.rocketseat.gestao_vagas.modules.company.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.andrey.rocketseat.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.andrey.rocketseat.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import com.andrey.rocketseat.gestao_vagas.modules.company.entities.CompanyEntity;
import com.andrey.rocketseat.gestao_vagas.modules.company.repository.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class AuthCompanyService {

    @Value("${security.token.secret}") //vem do aplication.properties
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        CompanyEntity company = this.companyRepository.findByEmail(authCompanyDTO.getEmail())
            .orElseThrow(
                () -> {
                    throw new IllegalArgumentException("Email não encontrado");
            }
        );

        //verificando se as senhas são iguais
        boolean password = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!password) {
            throw new AuthenticationException("Senha invalida!" );
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);//informa qual criptografia e passa a secret que esta no aplication.properties

        Instant expiresAt = Instant.now().plus(Duration.ofHours(4));

        String token = JWT.create().withIssuer("nomeDeQuemAssina")
        .withExpiresAt(expiresAt)//adiconando tempo de expiracao do token
        .withSubject(company.getId().toString())//informacao unica de qm vai usar o token
        .withClaim("roles", Arrays.asList("COMPANY"))//informacoes adicionais
        .sign(algorithm);


        AuthCompanyResponseDTO response = AuthCompanyResponseDTO.builder()
        .acess_token(token)
        .expires_in(expiresAt)
        .build();

        return response;
    }
}
