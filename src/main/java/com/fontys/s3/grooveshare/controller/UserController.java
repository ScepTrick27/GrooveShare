package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.FollowUseCase;
import com.fontys.s3.grooveshare.business.IsFollowingUseCase;
import com.fontys.s3.grooveshare.business.UnfollowUseCase;
import com.fontys.s3.grooveshare.business.dtos.FollowRequest;
import com.fontys.s3.grooveshare.business.dtos.UnfollowRequest;
import com.fontys.s3.grooveshare.business.dtos.userdto.*;
import com.fontys.s3.grooveshare.business.user.*;
import com.fontys.s3.grooveshare.configuration.security.token.AccessToken;
import com.fontys.s3.grooveshare.domain.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@EnableMethodSecurity
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@AllArgsConstructor
public class UserController {

    private final GetUserUseCase getUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final LogInUserUseCase loginUserUseCase;
    private final GetFilteredUsersUseCase getFilteredUsersUseCase;
    private final FollowUseCase followUseCase;
    private final UnfollowUseCase unfollowUseCase;
    private final IsFollowingUseCase isFollowingUseCase;

    @Autowired
    private AccessToken authenticatedUser;

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final long id) {
        final Optional<User> userOptional = getUserUseCase.getUser(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        GetAllUsersRequest request = GetAllUsersRequest.builder()
                .page(page)
                .size(size)
                .build();

        GetAllUsersResponse response = getUsersUseCase.getUsers(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        deleteUserUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateUserRequest request) {
        long authenticatedUserId = authenticatedUser.getUserId();

        if( id != authenticatedUserId){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        request.setUserId(id);
        updateUserUseCase.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/tokens")
    public ResponseEntity<LogInUserResponse> login(@RequestBody @Valid LogInUserRequest loginRequest) {
        LogInUserResponse loginResponse = loginUserUseCase.loginUser(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }

    @GetMapping(path = "/filter{username}")
    public ResponseEntity<GetFilteredUsersResponse> getFilteredUsers(@PathVariable String username){
        GetFilteredUsersResponse response = getFilteredUsersUseCase.filteredSearch(username);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/follow/{followerId}/{followeeId}")
    public ResponseEntity<Void> follow(@PathVariable Long followerId, @PathVariable Long followeeId) {
        FollowRequest request = new FollowRequest(followerId, followeeId);
        followUseCase.follow(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/unfollow/{followerId}/{followeeId}")
    public ResponseEntity<Void> unfollow(@PathVariable Long followerId, @PathVariable Long followeeId) {
        UnfollowRequest request = new UnfollowRequest(followerId, followeeId);
        unfollowUseCase.unfollow(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/isFollowing/{followerId}/{followeeId}")
    public ResponseEntity<Boolean> isFollowing(@PathVariable Long followerId, @PathVariable Long followeeId) {
        boolean isFollowing = isFollowingUseCase.isFollowing(followerId, followeeId);
        return ResponseEntity.ok(isFollowing);
    }
}
