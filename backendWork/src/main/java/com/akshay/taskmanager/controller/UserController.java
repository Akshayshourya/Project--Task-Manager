package com.akshay.taskmanager.controller;


import com.akshay.taskmanager.dto.LoginRequest;
import com.akshay.taskmanager.dto.UserRequest;
import com.akshay.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/users"))
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest request){
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request)
    {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }
}
