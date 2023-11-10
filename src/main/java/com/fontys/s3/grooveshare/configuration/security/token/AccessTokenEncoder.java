package com.fontys.s3.grooveshare.configuration.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
