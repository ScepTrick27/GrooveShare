package com.fontys.s3.grooveshare.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.s3.grooveshare.business.*;
import com.fontys.s3.grooveshare.business.dtos.*;
import com.fontys.s3.grooveshare.domain.Verification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class VerificationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetAllVerificationsUseCase getAllVerificationsUseCase;

    @MockBean
    private GetVerificationByIdUseCase getVerificationByIdUseCase;

    @MockBean
    private UpdateVerificationUseCase updateVerificationUseCase;

    @MockBean
    private CreateVerificationUseCase createVerificationUseCase;

    @MockBean
    private HasSentVerificationUseCase hasSentVerificationUseCase;

    @MockBean
    private VerifyUserUseCase verifyUserUseCase;

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testCreateVerification() throws Exception {
        CreateVerificationResponse response = CreateVerificationResponse.builder().userId(1L).build();
        Mockito.when(createVerificationUseCase.createVerification(Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/verifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateVerificationRequest())))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1L));

        Mockito.verify(createVerificationUseCase, Mockito.times(1)).createVerification(Mockito.any());
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testGetAllVerifications() throws Exception {
        GetAllVerificationsResponse response = GetAllVerificationsResponse.builder()
                .verificationList(Arrays.asList(new Verification(), new Verification()))
                .build();
        Mockito.when(getAllVerificationsUseCase.getAllVerifications(Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/verifications"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.verificationList").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.verificationList.length()").value(2));

        Mockito.verify(getAllVerificationsUseCase, Mockito.times(1)).getAllVerifications(Mockito.any());
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testGetVerification() throws Exception {
        Verification mockVerification = Verification.builder().id(1L).build();
        Mockito.when(getVerificationByIdUseCase.getVerification(Mockito.anyLong())).thenReturn(Optional.of(mockVerification));

        mockMvc.perform(MockMvcRequestBuilders.get("/verifications/{verificationId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));

        Mockito.verify(getVerificationByIdUseCase, Mockito.times(1)).getVerification(1L);

        Mockito.reset(getVerificationByIdUseCase);

        Mockito.when(getVerificationByIdUseCase.getVerification(Mockito.anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/verifications/{verificationId}", 2L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(getVerificationByIdUseCase, Mockito.times(1)).getVerification(2L);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testUpdateVerification() throws Exception {
        UpdateVerificationRequest updateRequest = new UpdateVerificationRequest();
        updateRequest.setVerificationId(1L);
        updateRequest.setVerificationStatus("ACCEPTED");

        mockMvc.perform(MockMvcRequestBuilders.put("/verifications/{verificationId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(updateVerificationUseCase, Mockito.times(1)).updateVerification(Mockito.eq(updateRequest));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testHasUserSentVerification() throws Exception {
        Long userId = 1L;

        HasSentVerificationResponse response = HasSentVerificationResponse.builder()
                .hasSentVerification(true)
                .build();

        Mockito.when(hasSentVerificationUseCase.hasSentVerification(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/verifications/hasSentVerification")
                        .param("userId", String.valueOf(userId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasSentVerification").value(true));  // Adjust based on your expected response

        Mockito.verify(hasSentVerificationUseCase, Mockito.times(1)).hasSentVerification(Mockito.any(HasSentVerificationRequest.class));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testVerifyUser() throws Exception {
        Long userId = 1L;
        VerifyUserRequest request = new VerifyUserRequest();
        request.setUserId(1L);
        request.setVerificationStatus("ACCEPTED");
        Mockito.doNothing().when(verifyUserUseCase).verifyUser(request);

        mockMvc.perform(MockMvcRequestBuilders.put("/verifications/verifyUser/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(verifyUserUseCase, Mockito.times(1)).verifyUser(request);
    }
}