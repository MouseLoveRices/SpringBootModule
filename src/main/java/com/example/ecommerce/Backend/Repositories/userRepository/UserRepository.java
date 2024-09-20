package com.example.ecommerce.Backend.Repositories.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.Backend.Modals.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserName(String userName);
    boolean existsByName(String name);
}