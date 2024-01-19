package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.*;
import com.fontys.s3.grooveshare.business.dtos.*;
import com.fontys.s3.grooveshare.domain.Verification;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@EnableMethodSecurity
@RequestMapping("/verifications")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@AllArgsConstructor
public class VerificationController {
    private final GetAllVerificationsUseCase getAllVerificationsUseCase;
    private final GetVerificationByIdUseCase getVerificationByIdUseCase;
    private final UpdateVerificationUseCase updateVerificationUseCase;
    private final CreateVerificationUseCase createVerificationUseCase;
    private final HasSentVerificationUseCase hasSentVerificationUseCase;
    private final VerifyUserUseCase verifyUserUseCase;

    @PostMapping()
    public ResponseEntity<CreateVerificationResponse> createVerification(@RequestBody @Valid CreateVerificationRequest request){
        CreateVerificationResponse response = createVerificationUseCase.createVerification(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<GetAllVerificationsResponse> getAllVerifications() {
        GetAllVerificationsRequest request = GetAllVerificationsRequest.builder().build();
        GetAllVerificationsResponse response = getAllVerificationsUseCase.getAllVerifications(request);
        return ResponseEntity.ok(response);
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/{verificationId}")
    public ResponseEntity<Verification> getVerification(@PathVariable("verificationId")Long verificationId){
        final Optional<Verification> verificationOptional = getVerificationByIdUseCase.getVerification(verificationId);
        if (verificationOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(verificationOptional.get());
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping("{verificationId}")
    public ResponseEntity<Void> updateVerification(@PathVariable("verificationId") Long verificationId,
                                           @RequestBody @Valid UpdateVerificationRequest request){
        request.setVerificationId(verificationId);
        updateVerificationUseCase.updateVerification(request);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hasSentVerification")
    public ResponseEntity<HasSentVerificationResponse> hasUserSentVerification(@RequestParam Long userId) {
        HasSentVerificationRequest request = HasSentVerificationRequest.builder().userId(userId).build();
        HasSentVerificationResponse response = hasSentVerificationUseCase.hasSentVerification(request);
        return ResponseEntity.ok(response);
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping("verifyUser/{userId}")
    public ResponseEntity<Void> verifyUser(@PathVariable("userId") long userId, @RequestBody @Valid VerifyUserRequest request) {
        request.setUserId(userId);
        verifyUserUseCase.verifyUser(request);
        return ResponseEntity.noContent().build();
    }
}
