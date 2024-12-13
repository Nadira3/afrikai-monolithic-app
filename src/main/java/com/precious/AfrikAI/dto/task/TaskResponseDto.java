package com.precious.AfrikAI.dto.task;

import java.time.LocalDateTime;

import com.precious.AfrikAI.model.task.TaskCategory;
import com.precious.AfrikAI.model.task.TaskStatus;

import lombok.Data;

// Task Response DTO
@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private double reward;
    private LocalDateTime createdAt;
    private String clientUsername;
    private String assignedTaskerUsername;
    private TaskCategory category;
}