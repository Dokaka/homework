package com.example.demo.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String username;
    private String email;
    private String address;
}
