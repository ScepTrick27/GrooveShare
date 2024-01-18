package com.fontys.s3.grooveshare.business.impl.userImpl;

import com.fontys.s3.grooveshare.business.userInterface.CreateUserUseCase;
import com.fontys.s3.grooveshare.business.dtos.userDtos.CreateUserRequest;
import com.fontys.s3.grooveshare.business.dtos.userDtos.CreateUserResponse;
import com.fontys.s3.grooveshare.persistance.RoleRepository;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.RoleEnum;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import com.fontys.s3.grooveshare.persistance.entity.UserRoleEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        UserEntity savedUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getUserId())
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);
        request.setUserRole(role);
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .description(request.getDescription())
                .userGender(request.getUserGender())
                .userRole(role)
                .photo(request.getPhoto())
                .isVerified(false)
                .build();
        return userRepository.save(newUser);
    }
}
