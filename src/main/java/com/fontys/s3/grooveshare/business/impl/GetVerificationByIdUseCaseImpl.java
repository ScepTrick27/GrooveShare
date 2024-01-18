package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.GetAllVerificationsUseCase;
import com.fontys.s3.grooveshare.business.GetVerificationByIdUseCase;
import com.fontys.s3.grooveshare.business.impl.postImpl.PostConverter;
import com.fontys.s3.grooveshare.domain.Post;
import com.fontys.s3.grooveshare.domain.Verification;
import com.fontys.s3.grooveshare.persistance.PostRepository;
import com.fontys.s3.grooveshare.persistance.VerificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetVerificationByIdUseCaseImpl implements GetVerificationByIdUseCase {
    private final VerificationRepository verificationRepository;

    @Override
    public Optional<Verification> getVerification(Long verificationId) {
        return verificationRepository.findById(verificationId).map(VerificationConverter::convert);
    }
}
