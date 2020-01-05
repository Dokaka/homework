package com.example.demo.controllertest;

import com.example.demo.MytestApplication;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


    @Test
    public void getUserByEmail() throws Exception {
        createTestUser("test@gmail.com");
        mvc.perform(get("/users/email/sam@gmail.com").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is("sam@gmail.com")));

    }
//    @Test
//    public void getUserInit() throws Exception{
//        createTestUser("test@gmail.com");
//        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect((status().isOk()))
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath())
//
//    }
    private void createTestUser(String email) throws ParseException {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setUsername("hahaha");
        userRepository.save(userEntity);
    }

}
