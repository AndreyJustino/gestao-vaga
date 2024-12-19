package com.andrey.rocketseat.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTProviders {

    @Value("${security.token.secret}")
    private String secretKey;

    public String validadeToken(String token){
        
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            String subjet = JWT.require(algorithm)
            .build()
            .verify(token)
            .getSubject();
        
            return subjet;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }

        
    }
}
