package com.fontys.s3.grooveshare.business;

import com.fontys.s3.grooveshare.business.dtos.GetAllVerificationsRequest;
import com.fontys.s3.grooveshare.business.dtos.GetAllVerificationsResponse;

public interface GetAllVerificationsUseCase {
    GetAllVerificationsResponse getAllVerifications(GetAllVerificationsRequest request);
}
