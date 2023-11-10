package com.fontys.s3.grooveshare.business.impl.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.time.Instant;
import java.util.UUID;

public class JWTService {
    public static String createJWT(Long id) {
        Algorithm algorithm = Algorithm.HMAC512("Change to ENV");
        return JWT.create()
                .withIssuer("grooveshare")
                .withSubject("user auth")
                .withClaim("userId", id)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(604800))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(Instant.now())
                .sign(algorithm);
    }

    public static DecodedJWT verifyJWT(String jwt) {
        try {

            Algorithm algorithm = Algorithm.HMAC512("Change to ENV");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("grooveshare")
                    .withSubject("user auth")
                    .build();

            return verifier.verify(jwt);
        } catch (Exception exception) {
            return null;
        }
    }

}
