package com.akshay.taskmanager.service;

import com.akshay.taskmanager.dto.LoginRequest;
import com.akshay.taskmanager.dto.UserRequest;
import com.akshay.taskmanager.dto.UserResponse;
import com.akshay.taskmanager.entity.Role;
import com.akshay.taskmanager.entity.User;
import com.akshay.taskmanager.exception.InvalidCredentialsException;
import com.akshay.taskmanager.repository.UserRepository;
import com.akshay.taskmanager.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        user.setRole(Role.USER);

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

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getRole()))
                .toList();
    }
}
