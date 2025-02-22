package com.andrey.rocketseat.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTCandidateProvider {

    @Value("${security.token.secret}")
    private String secretKey;

    public DecodedJWT validadeToken(String token){

        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        
        try{

            DecodedJWT subject = JWT.require(algorithm).build()
            .verify(token);

            return subject;

        }catch(JWTVerificationException e){

            return null;
        }
    }
}
