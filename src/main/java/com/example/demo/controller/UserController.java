package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IUserRepository;
import com.example.demo.request.UserRequest;
import com.example.demo.request.UserUpdateRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.response.UserUpdateResponse;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/users")
//@Api(value = "User APIs")
public class UserController {
    @Autowired
    private IUserService iUserService;

//    @ApiOperation(value="Create a user", response = UserDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 400, message="Bad request"),
//            @ApiResponse(code = 500, message="Internal Server Error"),
//    })
    @GetMapping()
    public List<UserDTO> getUserInit(){
        List<UserDTO> listUsers = iUserService.getAllUsers();
        return listUsers;
    }
    @GetMapping(path = "/username/{username}")
    public UserDTO getUserByName(@PathVariable String username){
        UserDTO userDTO = iUserService.getUserByName(username);

        return userDTO;
    }
    @GetMapping(path = "/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email){
        UserDTO userDTO = iUserService.getUserByEmail(email);
        return userDTO;
    }

    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userRequest,userDTO);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(iUserService.createUser(userDTO),userResponse);
        return userResponse;
    }
    @PutMapping(path = "/{username}")
    public UserUpdateResponse updateUser(@PathVariable String username, @RequestBody UserUpdateRequest userUpdateRequest){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userUpdateRequest,userDTO);
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        BeanUtils.copyProperties(iUserService.updateUser(username,userDTO),userUpdateResponse);
        return userUpdateResponse;
    }

    @DeleteMapping(path = "/{username}")
    public void deleteUser(@PathVariable String username)
    {
        iUserService.deleteUser(username);
    }

}
