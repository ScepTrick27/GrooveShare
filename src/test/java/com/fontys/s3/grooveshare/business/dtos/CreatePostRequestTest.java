package com.fontys.s3.grooveshare.business.dtos;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class CreatePostRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidCreatePostRequest() {
        CreatePostRequest request = new CreatePostRequest("Valid content", 1L);
        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    public void testBlankContent() {
        CreatePostRequest request = CreatePostRequest.builder()
                .content("")
                .userId(1L)
                .build();

        Set<ConstraintViolation<CreatePostRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Validation should fail for blank content");
        assertEquals(1, violations.size(), "There should be one violation");
        assertEquals("must not be blank", violations.iterator().next().getMessage(), "Incorrect violation message");
    }

    @Test
    public void testNullUserId() {
        CreatePostRequest request = CreatePostRequest.builder()
                .content("Valid content")
                .userId(null)
                .build();

        Set<ConstraintViolation<CreatePostRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Validation should fail for null userId");
        assertEquals(1, violations.size(), "There should be one violation");
        assertEquals("must not be null", violations.iterator().next().getMessage(), "Incorrect violation message");
    }

    @Test
    public void testValidUserId() {
        CreatePostRequest request = new CreatePostRequest("Valid content", 1L);
        assertTrue(validator.validate(request).isEmpty());
    }
}