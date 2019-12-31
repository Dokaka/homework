package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
     List<UserDTO> getAllUsers();
     UserDTO getUserByName(String username);
     UserDTO createUser(UserDTO userDTO);
     UserDTO updateUser(String username, UserDTO userDTO);
     UserDTO getUserByEmail(String email);
     void deleteUser(String userID);
}
