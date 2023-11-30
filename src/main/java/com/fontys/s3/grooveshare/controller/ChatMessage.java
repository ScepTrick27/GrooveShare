package com.fontys.s3.grooveshare.controller;

import lombok.Data;

@Data
public class ChatMessage {
    private String id;
    private String userId;
    private String from;
    private String message;
}
