package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.GetAllVerificationsUseCase;
import com.fontys.s3.grooveshare.business.dtos.GetAllVerificationsRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllVerificationsResponse;
import com.fontys.s3.grooveshare.domain.Verification;
import com.fontys.s3.grooveshare.persistance.VerificationRepository;
import com.fontys.s3.grooveshare.persistance.entity.VerificationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllVerificationsUseCaseImpl implements GetAllVerificationsUseCase {
    private final VerificationRepository verificationRepository;
    @Override
    public GetAllVerificationsResponse getAllVerifications(GetAllVerificationsRequest request) {
        List<VerificationEntity> results = verificationRepository.findAll();

        final GetAllVerificationsResponse response = new GetAllVerificationsResponse();
        List<Verification> verifications = results
                .stream()
                .map(verificationEntity -> {
                    Verification verification = VerificationConverter.convert(verificationEntity);
                    return verification;
                })
                .toList();
        response.setVerificationList(verifications);

        return response;
    }
}
