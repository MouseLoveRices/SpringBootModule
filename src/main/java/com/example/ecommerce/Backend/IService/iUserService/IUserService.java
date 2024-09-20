package com.example.ecommerce.Backend.IService.iUserService;

import java.util.List;

import com.example.ecommerce.Backend.Dtos.userDTO.UserDTO;
import com.example.ecommerce.Backend.Modals.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;
    User getUserById(Long userId);
    List<User> getAlluser();
    User saveUser(UserDTO UserDTO);
    User login(UserDTO userDTO);
    User updatePassword(Long userId, UserDTO userDTO) throws Exception;
    User updateUser(Long userId, UserDTO UserDTO);
    void deleteUser(Long userId);

}
