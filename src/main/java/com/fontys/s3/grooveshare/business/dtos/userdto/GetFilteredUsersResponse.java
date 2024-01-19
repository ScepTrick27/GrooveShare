package com.fontys.s3.grooveshare.business.dtos.userdto;

import com.fontys.s3.grooveshare.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFilteredUsersResponse {
    private List<User> users;
}
