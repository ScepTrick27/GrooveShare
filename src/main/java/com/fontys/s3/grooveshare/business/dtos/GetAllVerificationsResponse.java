package com.fontys.s3.grooveshare.business.dtos;

import com.fontys.s3.grooveshare.domain.Verification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllVerificationsResponse {
    private List<Verification> verificationList;
}
