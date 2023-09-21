package com.fontys.s3.grooveshare.business.impl;

import com.fontys.s3.grooveshare.business.UpdateUserUseCase;
import com.fontys.s3.grooveshare.domain.UpdateUserRequest;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    @Override
    public void updateUser(UpdateUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getUserId());

        UserEntity user = userOptional.get();
        updateFields(request, user);

    }

private void updateFields(UpdateUserRequest request, UserEntity user){
    user.setUsername(request.getUsername());
    user.setPassword((request.getPassword()));
    user.setDescription(request.getDescription());
    user.setUserGender(request.getUserGender());

}
}
