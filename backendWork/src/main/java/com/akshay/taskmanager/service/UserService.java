package com.akshay.taskmanager.service;

import com.akshay.taskmanager.dto.LoginRequest;
import com.akshay.taskmanager.dto.UserRequest;
import com.akshay.taskmanager.entity.User;
import com.akshay.taskmanager.exception.InvalidCredentialsException;
import com.akshay.taskmanager.repository.UserRepository;
import com.akshay.taskmanager.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public String login (LoginRequest request)
    {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new InvalidCredentialsException());

        boolean match = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!match)
            throw new InvalidCredentialsException();

        return JwtUtil.generateToken(user.getId(), user.getEmail());
    }
}
