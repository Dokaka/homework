package com.example.demo.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String address;
}
