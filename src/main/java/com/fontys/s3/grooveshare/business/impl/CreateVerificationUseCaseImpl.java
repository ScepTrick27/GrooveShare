package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.CreateVerificationUseCase;
import com.fontys.s3.grooveshare.business.dtos.CreateVerificationRequest;
import com.fontys.s3.grooveshare.business.dtos.CreateVerificationResponse;
import com.fontys.s3.grooveshare.business.dtos.postDtos.CreatePostRequest;
import com.fontys.s3.grooveshare.business.dtos.postDtos.CreatePostResponse;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.VerificationRepository;
import com.fontys.s3.grooveshare.persistance.entity.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateVerificationUseCaseImpl implements CreateVerificationUseCase {
    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;

    @Transactional
    @Override
    public CreateVerificationResponse createVerification(CreateVerificationRequest request) {
        VerificationEntity savedVerification = saveNewVerification(request);

        if (savedVerification != null) {
            return CreateVerificationResponse.builder()
                    .userId(savedVerification.getUser().getUserId())
                    .status(savedVerification.getStatus().toString())
                    .build();
        } else {
            return CreateVerificationResponse.builder()
                    .build();
        }
    }

    private VerificationEntity saveNewVerification(CreateVerificationRequest request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());

        if (user.isPresent()) {
            VerificationEntity newVerification = VerificationEntity.builder()
                    .verificationPhoto(request.getVerificationPhoto())
                    .status(VerificationStatusEntity.PENDING)
                    .user(user.get())
                    .build();
            return verificationRepository.save(newVerification);
        } else {
            return null;
        }
    }
}
