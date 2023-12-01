package com.fontys.s3.grooveshare.configuration.exceptionhandler;

import com.fontys.s3.grooveshare.business.*;
import com.fontys.s3.grooveshare.config.TestConfig;
import com.fontys.s3.grooveshare.controller.UserController;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.slf4j.Logger; // Import the SLF4J Logger
import org.slf4j.LoggerFactory; // Import the LoggerFactory
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import({ TestConfig.class })
@ExtendWith(SpringExtension.class)
class RestCustomExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private Logger logger;

    @MockBean
    private GetUserUseCase getUserUseCase;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @MockBean
    private GetUsersUseCase getUsersUseCase;

    @MockBean
    private UpdateUserUseCase updateUserUseCase;
    @MockBean
    private DeleteUserUseCase deleteUserUseCase;

    @MockBean
    private LogInUserUseCase loginUserUseCase;

    @Mock
    private GetUsersUseCase getUsersUseCase2;
    @InjectMocks
    private UserController userController;

    @InjectMocks
    private RestCustomExceptionHandler restCustomExceptionHandler;

    @Test
    public void testHandleConstraintViolationException() throws Exception {
        // Arrange
        AccessDeniedException accessDeniedException = mock(AccessDeniedException.class);

        // Act & Assert
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void testHandleResponseStatusException() throws Exception {
        // Arrange
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        String reason = "Resource not found";
        ResponseStatusException responseStatusException = new ResponseStatusException(statusCode, reason);

        // Act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void testHandleUnknownRuntimeError() throws Exception {
        // Arrange
        // Simulate an internal server error by throwing a RuntimeException
        when(userController.getAllUsers()).thenThrow(new RuntimeException("Some internal error message"));

        // Act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    }