package com.andrey.rocketseat.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTCompanyProviders {

    @Value("${security.token.secret}")
    private String secretKey;

    public DecodedJWT validadeToken(String token){
        
        token = token.replace("Bearer ", "");
        
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            DecodedJWT tokenDecoded = JWT.require(algorithm)
            .build()
            .verify(token);
        
            System.out.println("===== JWTCompanyProviders try ====");

            return tokenDecoded;
        } catch (JWTVerificationException e) {

            System.out.println("===== JWTCompanyProviders catch ====");

            return null;
        }

        
    }
}
