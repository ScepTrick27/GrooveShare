package com.fontys.s3.grooveshare.business.impl.user;

import com.fontys.s3.grooveshare.business.dtos.userdto.LogInUserRequest;
import com.fontys.s3.grooveshare.business.dtos.userdto.LogInUserResponse;
import com.fontys.s3.grooveshare.business.user.LogInUserUseCase;
import com.fontys.s3.grooveshare.business.exception.InvalidCredentialsException;
import com.fontys.s3.grooveshare.configuration.security.token.AccessTokenEncoder;
import com.fontys.s3.grooveshare.configuration.security.token.impl.AccessTokenImpl;
import com.fontys.s3.grooveshare.persistance.UserRepository;
import com.fontys.s3.grooveshare.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LogInUserUseCaseImpl implements LogInUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;
    @Override
    public LogInUserResponse loginUser(LogInUserRequest request) {

        UserEntity user = userRepository.getUserEntityByUsername(request.getUsername());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LogInUserResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getUsername(), user.getUserId(), List.of(user.getUserRole().getRole().toString())));
    }

}
