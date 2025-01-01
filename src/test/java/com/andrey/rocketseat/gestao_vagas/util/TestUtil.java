package com.andrey.rocketseat.gestao_vagas.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class TestUtil {

    public static String objectToJson(Object obj){
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static String generateTokenCompany(UUID idCompany){
        Algorithm algorithm = Algorithm.HMAC256("secretQueTemQuePassa");//informa qual criptografia e passa a secret que esta no aplication.properties

        Instant expiresAt = Instant.now().plus(Duration.ofHours(4));

        String token = JWT.create().withIssuer("nomeDeQuemAssina")
                .withExpiresAt(expiresAt)
                .withSubject(idCompany.toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        return token;
    }
}
