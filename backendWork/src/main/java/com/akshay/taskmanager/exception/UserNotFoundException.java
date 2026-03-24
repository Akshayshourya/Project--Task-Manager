package com.akshay.taskmanager.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId){
        super("no user found with the id: "+userId);
    }
}
