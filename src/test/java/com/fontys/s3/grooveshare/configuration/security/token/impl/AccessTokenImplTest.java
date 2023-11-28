package com.fontys.s3.grooveshare.configuration.security.token.impl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class AccessTokenImplTest {

    @Test
    void constructorAndGetters() {
        String subject = "user123";
        Long userId = 1L;
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");

        AccessTokenImpl accessToken = new AccessTokenImpl(subject, userId, roles);

        assertEquals(subject, accessToken.getSubject());
        assertEquals(userId, accessToken.getUserId());
        assertEquals(Set.of("ROLE_USER", "ROLE_ADMIN"), accessToken.getRoles());
    }

    @Test
    void constructorWithNullRoles() {
        String subject = "user123";
        Long userId = 1L;

        AccessTokenImpl accessToken = new AccessTokenImpl(subject, userId, null);

        assertEquals(subject, accessToken.getSubject());
        assertEquals(userId, accessToken.getUserId());
        assertTrue(accessToken.getRoles().isEmpty());
    }

    @Test
    void constructorWithEmptyRoles() {
        String subject = "user123";
        Long userId = 1L;

        AccessTokenImpl accessToken = new AccessTokenImpl(subject, userId, Collections.emptyList());

        assertEquals(subject, accessToken.getSubject());
        assertEquals(userId, accessToken.getUserId());
        assertTrue(accessToken.getRoles().isEmpty());
    }

    @Test
    void hasRole() {
        String subject = "user123";
        Long userId = 1L;
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");

        AccessTokenImpl accessToken = new AccessTokenImpl(subject, userId, roles);

        assertTrue(accessToken.hasRole("ROLE_USER"));
        assertTrue(accessToken.hasRole("ROLE_ADMIN"));
        assertFalse(accessToken.hasRole("ROLE_OTHER"));
    }
}