package com.fontys.s3.grooveshare.business.impl.userImpl;

import com.fontys.s3.grooveshare.business.dtos.userDtos.UpdateUserRequest;
import com.fontys.s3.grooveshare.business.userInterface.UpdateUserUseCase;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.business.exception.InvalidUserIdException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public void updateUser(UpdateUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getUserId());

        if (userOptional.isEmpty()) {
            throw new InvalidUserIdException("USER_ID_INVALID");
        }

            UserEntity user = userOptional.get();
            updateFields(request, user);

    }

private void updateFields(UpdateUserRequest request, UserEntity user){
    user.setUsername(request.getUsername());
    user.setDescription(request.getDescription());

}
}
