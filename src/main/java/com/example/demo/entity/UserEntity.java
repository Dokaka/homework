package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "users")

@Data
public class UserEntity extends BaseEntity {
    //private static final long serialVersionUID = -297553281792804396L;
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String email;

    private String address;
}
