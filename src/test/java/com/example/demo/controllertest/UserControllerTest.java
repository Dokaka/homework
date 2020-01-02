package com.example.demo.controllertest;

import com.example.demo.controller.UserController;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IUserRepository;
import com.example.demo.request.UserRequest;
import com.example.demo.request.UserUpdateRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.response.UserUpdateResponse;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private UserDTO userDTO;
    private UserResponse userResponse;
    private UserUpdateResponse userUpdateResponse;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @BeforeEach
    void userInit(){
        MockitoAnnotations.initMocks(this);
        userDTO = new UserDTO();
        userDTO.setUsername("usernameTest");
        userDTO.setEmail("emailTest@gmail.com");
        userDTO.setAddress("addressTest");
    }
    @Test
    void testGetUserInit(){
//        List<UserDTO> userDTOList = new CopyOnWriteArrayList<>();
//        userDTOList.add(userDTO);
//        when(userService.getAllUsers()).thenReturn(userDTOList);
//        UserDTO m_userDTO = new UserDTO();
//        m_userDTO.setUsername(userDTO.getUsername());
//        List<UserDTO> m_userDTOList = userController.getUserInit();
//
//        assertNotNull(m_userDTOList);
//        assertEquals(m_userDTOList.get(0).getUsername(),m_userDTO.getUsername());
    }
    @Test
    void testGetUserByName(){
        when(userService.getUserByName(anyString())).thenReturn(userDTO);
        UserDTO m_userDTO = userController.getUserByName(anyString());
        assertNotNull(m_userDTO);
        assertEquals(m_userDTO.getUsername(),userDTO.getUsername());
    }
    @Test
    void testGetUserByEmail(){
        when(userService.getUserByEmail(anyString())).thenReturn(userDTO);
        UserDTO m_userDTO = userController.getUserByEmail(anyString());
        assertNotNull(m_userDTO);
        assertEquals(m_userDTO.getUsername(),userDTO.getUsername());
    }
    @Test
    void testCreateUser(){
        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

        UserRequest userRequest = new UserRequest();
        userResponse = userController.createUser(userRequest);

        assertNotNull(userResponse);
        assertEquals(userResponse.getUsername(),userDTO.getUsername());
    }
    @Test
    void testUpdateUser(){
        when(userService.updateUser(anyString(),any(UserDTO.class))).thenReturn(userDTO);
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateResponse = userController.updateUser("",userUpdateRequest);
        assertNotNull(userUpdateResponse);
        assertEquals(userUpdateResponse.getUsername(),userDTO.getUsername());
    }
    @Test
    void testDeleteUser(){
        //when(userRepository.findByUsername(anyString())).thenReturn(new UserEntity());
        // because of function "void deleteUser()" so it doesn't have return values
        // how to unit test it
    }

}
