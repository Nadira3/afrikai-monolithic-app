package com.precious.AfrikAI.dto.task;

import com.precious.AfrikAI.model.task.TaskCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Task Creation DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreationDto {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Reward must be positive")
    private double reward;

    @NotNull(message = "Category is required")
    private TaskCategory category;
}