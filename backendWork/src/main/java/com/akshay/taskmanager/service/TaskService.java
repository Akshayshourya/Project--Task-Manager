package com.akshay.taskmanager.service;

import com.akshay.taskmanager.dto.TaskRequest;
import com.akshay.taskmanager.dto.TaskResponse;
import com.akshay.taskmanager.entity.Task;
import com.akshay.taskmanager.entity.User;
import com.akshay.taskmanager.exception.TaskNotFoundException;
import com.akshay.taskmanager.exception.TaskOwnerException;
import com.akshay.taskmanager.exception.UserNotFoundException;
import com.akshay.taskmanager.repository.TaskRepository;
import com.akshay.taskmanager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(Long userid, TaskRequest request){
        User user = userRepository.findById(userid).orElseThrow(()->new UserNotFoundException(userid));
        Task task = new Task();

        task.setUser(user);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(false);

        return taskRepository.save(task);
    }

    public List<TaskResponse> getTasksOfUser(Long userid){
        userRepository.findById(userid).orElseThrow(() -> new UserNotFoundException(userid) );

        return taskRepository.findByUserId(userid).stream().map(Task -> new TaskResponse(
                Task.getId(),
                Task.getTitle(),
                Task.getDescription(),
                Task.isCompleted()
        )).toList();
    }

    public void markTaskCompleted(Long userId, Long taskId) {

        // ensure user exists
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        // business rule: task must belong to user
        if (!task.getUser().getId().equals(userId)) {
            throw new TaskOwnerException();
        }

        task.setCompleted(true);
        taskRepository.save(task);
    }
}
