package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.VerifyUserUseCase;
import com.fontys.s3.grooveshare.business.dtos.VerifyUserRequest;
import com.fontys.s3.grooveshare.business.exception.InvalidUserIdException;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.VerificationStatusEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class VerifiyUserUseCaseImpl implements VerifyUserUseCase {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public void verifyUser(VerifyUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getUserId());

        if (userOptional.isEmpty()) {
            throw new InvalidUserIdException("USER_ID_INVALID");
        }

        UserEntity user = userOptional.get();
        updateFields(request, user);

    }

    private void updateFields(VerifyUserRequest request, UserEntity user){
        if (Enum.valueOf(VerificationStatusEntity.class, request.getVerificationStatus()) == VerificationStatusEntity.ACCEPTED) {
            user.setVerified(true);
        }
    }
}
