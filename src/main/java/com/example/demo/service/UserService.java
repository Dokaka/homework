package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    //private List<UserDTO> userDTOList = new CopyOnWriteArrayList<>();
    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDTO> m_userDTOList = new CopyOnWriteArrayList<>();

//        if(userDTOList.size() == 0)
//        {
            for (UserEntity userEntity:userEntityList)
            {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userEntity,userDTO);
                m_userDTOList.add(userDTO);
            }
//        }

        return  m_userDTOList;

    }

    @Override
    public UserDTO getUserByName(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity,userDTO);
        return userDTO;
    }
    @Override
    public UserDTO getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity,userDTO);
        return userDTO;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO,userEntity);
        UserDTO m_userDTO = new UserDTO();
        BeanUtils.copyProperties(userRepository.save(userEntity),m_userDTO);

        //userDTOList.add(m_userDTO);
        return m_userDTO;
    }
    @Override
    public UserDTO updateUser(String username,UserDTO userDTO)
    {
        String updateEmail = userDTO.getEmail();
        String updateAddress = userDTO.getAddress();
        String updateUsername = userDTO.getUsername();

        UserEntity userEntity = userRepository.findByUsername(username);
        if (null == userEntity) throw new RuntimeException("User not found");
        if(!updateAddress.isEmpty() &&!updateEmail.isEmpty() &&!updateUsername.isEmpty()) {
            userEntity.setEmail(updateEmail);
            userEntity.setAddress(updateAddress);
            userEntity.setUsername(updateUsername);
        }
        else
        {
            System.out.println("can't update");
        }

        //BeanUtils.copyProperties(userDTO,userEntity);
        UserDTO m_userDTO = new UserDTO();
        BeanUtils.copyProperties(userRepository.save(userEntity),m_userDTO);
        return  m_userDTO;
    }

    @Override
    public void deleteUser(String username )
    {

        //Long ID = Long.parseLong(userId);
        UserEntity userEntity = userRepository.findByUsername(username);
        userRepository.delete(userEntity);
        //Integer userID = Integer.parseInt(userId);
        //userDTOList.remove(ID);
    }
}