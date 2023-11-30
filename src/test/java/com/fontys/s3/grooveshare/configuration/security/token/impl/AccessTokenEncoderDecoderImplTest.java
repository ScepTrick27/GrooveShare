package com.fontys.s3.grooveshare.configuration.security.token.impl;

import com.fontys.s3.grooveshare.configuration.security.token.AccessToken;
import com.fontys.s3.grooveshare.configuration.security.token.exception.InvalidAccessTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.CollectionUtils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AccessTokenEncoderDecoderImplTest {

    private final AccessTokenEncoderDecoderImpl encoderDecoder = new AccessTokenEncoderDecoderImpl("E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5");

    @Test
    void encodeAndDecodeAccessToken() {
        AccessToken accessToken = new AccessTokenImpl("subject", 1L, List.of("ROLE_USER"));

        String encodedToken = encoderDecoder.encode(accessToken);
        AccessToken decodedToken = encoderDecoder.decode(encodedToken);

        assertEquals(accessToken.getSubject(), decodedToken.getSubject());
        assertEquals(accessToken.getUserId(), decodedToken.getUserId());
        assertEquals(accessToken.getRoles(), decodedToken.getRoles());
    }

    @Test
    void decodeInvalidAccessTokenThrowsException() {
        assertThrows(InvalidAccessTokenException.class, () -> {
            String invalidToken = "invalid_token";
            encoderDecoder.decode(invalidToken);
        });
    }

    @Test
    void constructWithSecretKey() {
        Key retrievedKey = (Key) ReflectionTestUtils.getField(encoderDecoder, "key");

        assertNotNull(retrievedKey);
    }

    @Test
    void decodeTokenWithMissingClaims() {
        String tokenWithMissingClaims = "yourInvalidTokenString";

        assertThrows(InvalidAccessTokenException.class, () -> encoderDecoder.decode(tokenWithMissingClaims));
    }
@Test
    void decodeTokenWithUnsupportedAlgorithm() {
        AccessToken accessToken = new AccessTokenImpl("user123", 1L, Collections.singletonList("ROLE_USER"));

        String tokenWithUnsupportedAlgorithm = encodeWithHmacSha256(accessToken);

        assertThrows(InvalidAccessTokenException.class, () -> encoderDecoder.decode(tokenWithUnsupportedAlgorithm));
    }

    private String encodeWithHmacSha256(AccessToken accessToken) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        Map<String, Object> claimsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(accessToken.getRoles())) {
            claimsMap.put("roles", accessToken.getRoles());
        }
        if (accessToken.getUserId() != null) {
            claimsMap.put("userId", accessToken.getUserId());
        }

        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

    @Test
    void decodeTamperedToken() {
        AccessToken accessToken = new AccessTokenImpl("user123", 1L, Collections.singletonList("ROLE_USER"));
        String tamperedToken = encoderDecoder.encode(accessToken) + "tampered";

        assertThrows(InvalidAccessTokenException.class, () -> encoderDecoder.decode(tamperedToken));
    }

}