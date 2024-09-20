package com.example.ecommerce.Backend.Service.userService;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.ecommerce.Backend.Dtos.userDTO.UserDTO;
import com.example.ecommerce.Backend.Exceptions.DataNotFoundException;
import com.example.ecommerce.Backend.IService.iUserService.IUserService;
import com.example.ecommerce.Backend.Modals.User;
import com.example.ecommerce.Backend.Repositories.userRepository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    
    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        String name = userDTO.getName();
        if(userRepository.existsByName(name)) {
            throw new DataNotFoundException("Name '"+ name + "' already exists");
        }

        User newUser = User
                .builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .password(userDTO.getPassword())
                .userName(userDTO.getUserName())
                .address(userDTO.getAddress())
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Không tìm thấy user"));
    }

    @Override
    public List<User> getAlluser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAlluser'");
    }


    /// CHƯA BĂM PASSWORD
    @Override
    public User saveUser(UserDTO UserDTO) {
       User user = User.builder()
                    .name(UserDTO.getName())
                    .userName(UserDTO.getUserName())
                    .email(UserDTO.getEmail())
                    .password(UserDTO.getPassword())
                    .phone(UserDTO.getPhone())
                    .address(UserDTO.getAddress())
                    .build();
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, UserDTO UserDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updatePassword(Long userId, UserDTO userDTO) throws Exception {

    User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new DataNotFoundException("User not found"));

    if (userDTO.getPassword() == null || userDTO.getPassword().length() < 8) {
        throw new IllegalArgumentException("Password must be at least 8 characters long.");
    }

    existingUser.setPassword(userDTO.getPassword());

    return userRepository.save(existingUser);
    }

    @Override
    public User login(UserDTO userDTO) {
        User existingUser = userRepository.findByUserName(userDTO.getName());
                                        
        if(!existingUser.getPassword().equals(userDTO.getPassword())){
            throw new IllegalArgumentException("Incorrect password");
        }

        return existingUser;
    }




    
}
