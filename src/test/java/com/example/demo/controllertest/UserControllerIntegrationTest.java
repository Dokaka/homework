package com.example.demo.controllertest;

import com.example.demo.MytestApplication;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IUserRepository;
import com.example.demo.request.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MytestApplication.class)
@AutoConfigureMockMvc

public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private IUserRepository userRepository;
    @AfterTestClass
    public void resetDb() {
        userRepository.deleteAll();
    }
//    @Test
//    public void createUser() throws Exception{
//
//        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.username",is("testUsername")));
//
//    }


    @Test
    public void getUserByEmail() throws Exception {
        createTestUser("test@gmail.com");
        mvc.perform(get("/users/email/test@gmail.com").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is("test@gmail.com")));
    }
    @Test
    public void getUserByUsername() throws Exception{
        createTestUsername("hahaha");
        mvc.perform(get("/users/username/hahaha").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("hahaha")));
    }
    @Test
    public void getUserInit() throws Exception{
        mvc.perform( MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").exists());
    }



    @Test
    public void createUser() throws Exception{
        //UserEntity userEntity = new UserEntity();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testCreateUser111");
        userDTO.setEmail("testCreateUser111@gmail.com");
        userDTO.setAddress("england");
        //userRepository.save(userEntity);

        mvc.perform( MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username",is("testCreateUser111")));
    }
    @Test
    public void updateUser() throws Exception{

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testCreateUser222");
        userDTO.setEmail("testCreateUser222@gmail.com");
        userDTO.setAddress("hanoi");
        mvc.perform( MockMvcRequestBuilders
                .put("/users/hahaha")
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testCreateUser222"));

    }
    @Test
    public void deleteUser() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders.delete("/users/{username}", "testUsername") )
                .andExpect(status().isOk());
    }

    private void createTestUser(String email) throws ParseException {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setUsername("testUsername");
        userRepository.save(userEntity);
    }
    private void createTestUsername(String username) throws ParseException{
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("testEmail");
        userEntity.setUsername(username);
        System.out.println(userRepository.save(userEntity));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
