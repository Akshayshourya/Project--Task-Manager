package com.akshay.taskmanager.exception;

public class TaskOwnerException extends RuntimeException{
    public TaskOwnerException() {
        super("The task does not belong to this user!");
    }
}
