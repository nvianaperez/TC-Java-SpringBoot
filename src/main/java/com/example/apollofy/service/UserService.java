package com.example.apollofy.service;

import com.example.apollofy.domain.User;
import com.example.apollofy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) throw new RuntimeException("User id " +userId+ " is invalid");
        User user = optionalUser.get();
        return user;
    }
}
