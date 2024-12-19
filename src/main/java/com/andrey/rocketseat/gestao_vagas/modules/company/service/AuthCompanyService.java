package com.andrey.rocketseat.gestao_vagas.modules.company.service;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.andrey.rocketseat.gestao_vagas.modules.company.dto.AuthCompanyDTO;
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

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
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
        String token = JWT.create().withIssuer("nomeDeQuemAssina")
        .withExpiresAt(Instant.now().plus(Duration.ofHours(4)))//adiconando tempo de expiracao do token
        .withSubject(company.getId().toString())//informacao unica de qm vai usar o token
        .sign(algorithm);

        return token;
    }
}
