package com.example.demo.repository;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface IUserRepository extends CrudRepository<UserEntity,Long> {
    List<UserEntity> findAll();
    UserEntity findByUsername(String username);
    //UserEntity findById(String userID);
    UserEntity findById(Integer ID);
    UserEntity findByEmail(String email);
}
