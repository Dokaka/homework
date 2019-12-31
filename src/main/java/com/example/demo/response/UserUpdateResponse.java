package com.example.demo.response;

import lombok.Data;

@Data
public class UserUpdateResponse {
    private String username;
    private String email;
    private String address;
}
