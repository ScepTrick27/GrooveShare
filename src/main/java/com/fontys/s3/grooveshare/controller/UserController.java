package com.fontys.s3.grooveshare.controller;

import com.fontys.s3.grooveshare.business.*;
import com.fontys.s3.grooveshare.business.DTOs.*;
import com.fontys.s3.grooveshare.domain.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = { "http://localhost:5173" })
@AllArgsConstructor
public class UserController {

private final GetUserUseCase getUserUseCase;
private final DeleteUserUseCase deleteUserUseCase;
private final GetUsersUseCase getUsersUseCase;
private final CreateUserUseCase createUserUseCase;
private final UpdateUserUseCase updateUserUseCase;

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final long id) {
        final Optional<User> userOptional = getUserUseCase.getUser(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        GetAllUsersRequest request = GetAllUsersRequest.builder().build();
        GetAllUsersResponse response = getUsersUseCase.getUsers(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        deleteUserUseCase.DeleteUser(userId);
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
        request.setUserId(id);
        updateUserUseCase.updateUser(request);
        return ResponseEntity.noContent().build();
    }
}
