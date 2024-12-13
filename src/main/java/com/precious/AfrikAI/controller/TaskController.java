package com.precious.AfrikAI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import jakarta.validation.Valid;

import com.precious.AfrikAI.dto.task.TaskCreationDto;
import com.precious.AfrikAI.model.task.Task;
import com.precious.AfrikAI.model.user.User;
import com.precious.AfrikAI.repository.UserRepository;
import com.precious.AfrikAI.security.custom.CustomUserDetails;
import com.precious.AfrikAI.service.task.TaskService;
import com.precious.AfrikAI.service.user.UserService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(
        @AuthenticationPrincipal CustomUserDetails userDetails, 
        @Valid @RequestBody TaskCreationDto taskDto
    ) {
        User client = userDetails.getUser();
        Task createdTask = taskService.createTask(client, taskDto);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Task>> getAvailableTasks() {
        List<Task> availableTasks = taskService.findAvailableTasks();
        return ResponseEntity.ok(availableTasks);
    }

    // More endpoints for task assignment, submission, review, etc.
}

