package com.akshay.taskmanager.service;

import com.akshay.taskmanager.dto.UserRequest;
import com.akshay.taskmanager.entity.User;
import com.akshay.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }
}
