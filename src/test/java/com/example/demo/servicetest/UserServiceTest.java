package com.example.demo.servicetest;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserEntity userEntity;
    private UserDTO userDTO;

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @BeforeEach
    void serviceUserInit(){
        MockitoAnnotations.initMocks(this);
        userDTO = new UserDTO();
        userDTO.setUsername("usernameDTOTest");
        userDTO.setEmail("emailDTOTest@gmail.com");
        userDTO.setAddress("addressDTOTest");

        userEntity = new UserEntity();
        userEntity.setUsername("usernameEntityTest");
        userEntity.setEmail("emailEntitytest@gmail.com");
        userEntity.setAddress("addressEntityTest");
    }
    @Test
    void testGetAllUsers(){
        List<UserEntity> userEntityList = new CopyOnWriteArrayList<>();
        userEntityList.add(userEntity);
        when(userRepository.findAll()).thenReturn(userEntityList);
        List<UserDTO> userDTOList = userService.getAllUsers();
        assertNotNull(userDTOList);
        assertEquals(userDTOList.get(0).getUsername(),userEntity.getUsername());
    }
    @Test
    void testGetUserByName(){
        when(userRepository.findByUsername(anyString())).thenReturn(userEntity);
        UserDTO m_userDTO = userService.getUserByName(anyString());
        assertNotNull(m_userDTO);
        assertEquals(userEntity.getUsername(),m_userDTO.getUsername());
    }
    @Test
    void testGetUserByEmail(){
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        UserDTO m_userDTO = userService.getUserByEmail(anyString());
        assertNotNull(m_userDTO);
        assertEquals(userEntity.getUsername(),m_userDTO.getUsername());
    }
    @Test
    void testCreateUser(){
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        UserDTO m_userDTO = userService.createUser(userDTO);
        assertNotNull(m_userDTO);
        assertEquals(userEntity.getUsername(),m_userDTO.getUsername());

    }
    @Test
    void testUpdateUser(){
        when(userRepository.findByUsername(anyString())).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        UserDTO m_userDTO = userService.updateUser(anyString(),userDTO);
        assertNotNull(m_userDTO);
        assertEquals(userEntity.getUsername(),m_userDTO.getUsername());
    }
    @Test
    void testDeleteUser(){
        // function deleteUser() in UserService doesn't have return value
    }


}
