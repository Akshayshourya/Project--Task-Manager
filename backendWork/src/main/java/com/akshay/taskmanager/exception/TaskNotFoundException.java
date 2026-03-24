package com.akshay.taskmanager.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Long taskId){
        super("No task found with the task id: "+taskId);
    }
}
