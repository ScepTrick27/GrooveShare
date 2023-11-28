package com.fontys.s3.grooveshare.business.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCredentialsExceptionTest {
    @Test
    public void testInvalidCredentialsException() {
        InvalidCredentialsException exception = new InvalidCredentialsException();

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("INVALID_CREDENTIALS", exception.getReason());
    }

    @Test
    public void testThrowingInvalidCredentialsException() {
        assertThrows(InvalidCredentialsException.class, () -> {
            throw new InvalidCredentialsException();
        });
    }

}