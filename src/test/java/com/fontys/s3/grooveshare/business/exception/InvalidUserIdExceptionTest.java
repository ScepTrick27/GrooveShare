package com.fontys.s3.grooveshare.business.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class InvalidUserIdExceptionTest {

    @Test
    public void testInvalidUserIdExceptionWithCustomErrorCode() {
        String customErrorCode = "CUSTOM_ERROR_CODE";
        InvalidUserIdException exception = new InvalidUserIdException(customErrorCode);

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(customErrorCode, exception.getReason());
    }

    @Test
    public void testThrowingInvalidUserIdExceptionWithCustomErrorCode() {
        assertThrows(InvalidUserIdException.class, () -> {
            throw new InvalidUserIdException("CUSTOM_ERROR_CODE");
        });
    }
}