package com.akshay.taskmanager.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super("Invalid Username OR Password!");
    }
}
