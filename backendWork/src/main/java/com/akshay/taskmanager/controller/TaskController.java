package com.akshay.taskmanager.controller;

import com.akshay.taskmanager.dto.TaskRequest;
import com.akshay.taskmanager.dto.TaskResponse;
import com.akshay.taskmanager.entity.Task;
import com.akshay.taskmanager.repository.TaskRepository;
import com.akshay.taskmanager.security.CustomUserDetails;
import com.akshay.taskmanager.service.TaskService;
import com.sun.management.VMOption;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskRepository taskRepository, TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<String> createTask(@AuthenticationPrincipal CustomUserDetails userDetails, @Valid @RequestBody TaskRequest request){
        Long userid = userDetails.getId();
        taskService.createTask(userid,request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Task created successfully");
    }

    @GetMapping
    public Page<TaskResponse> getTasks(@AuthenticationPrincipal CustomUserDetails userDetails, @ParameterObject Pageable pageable, @RequestParam(required = false) Boolean completed){
        Long userid = userDetails.getId();
        return taskService.getTasksOfUser(userid, pageable, completed);
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<String> completeTask(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long taskId) {
        Long userid = userDetails.getId();
        taskService.markTaskCompleted(userid, taskId);
        return ResponseEntity.ok("Task Marked as Complete!");
    }

}
