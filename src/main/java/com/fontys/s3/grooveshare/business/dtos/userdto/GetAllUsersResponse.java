package com.fontys.s3.grooveshare.business.dtos.userdto;

import com.fontys.s3.grooveshare.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersResponse {
    private List<User> users;
    private int totalPages;
    private long totalUsers;
}
