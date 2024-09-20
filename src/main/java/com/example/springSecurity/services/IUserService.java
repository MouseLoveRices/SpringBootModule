package com.example.springSecurity.services;

import com.example.springSecurity.models.User;
import java.util.List;

public interface IUserService {
    User createUser(User user);
    List<User> getAllUser();
    User getUserByUserName(String username);
    String login(String username, String password);
}
