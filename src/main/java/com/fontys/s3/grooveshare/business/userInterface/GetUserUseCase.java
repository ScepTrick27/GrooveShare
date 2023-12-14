package com.fontys.s3.grooveshare.business.userInterface;

import com.fontys.s3.grooveshare.domain.User;

import java.util.Optional;

public interface GetUserUseCase {
     Optional<User> getUser(long userId);
}