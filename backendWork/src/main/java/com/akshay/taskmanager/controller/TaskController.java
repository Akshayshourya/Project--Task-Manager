package com.akshay.taskmanager.controller;

import com.akshay.taskmanager.dto.TaskRequest;
import com.akshay.taskmanager.dto.TaskResponse;
import com.akshay.taskmanager.entity.Task;
import com.akshay.taskmanager.repository.TaskRepository;
import com.akshay.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userid}/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskRepository taskRepository, TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<String> createTask(@PathVariable Long userid, @Valid @RequestBody TaskRequest request){
        taskService.createTask(userid,request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Task created successfully");
    }

    @GetMapping
    public List<TaskResponse> getTasks(@PathVariable Long userid){
        return taskService.getTasksOfUser(userid);
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<String> completeTask(
            @PathVariable Long userid,
            @PathVariable Long taskId
    ) {
        taskService.markTaskCompleted(userid, taskId);
        return ResponseEntity.ok("Task Marked as Complete!");
    }

}
