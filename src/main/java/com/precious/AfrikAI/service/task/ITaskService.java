package com.precious.AfrikAI.service.task;

import java.util.List;

import com.precious.AfrikAI.dto.task.TaskCreationDto;
import com.precious.AfrikAI.model.task.Task;
import com.precious.AfrikAI.model.task.TaskCategory;
import com.precious.AfrikAI.model.user.Client;
import com.precious.AfrikAI.model.user.User;

public interface ITaskService {
    // Task Creation
    Task createTask(Client client, TaskCreationDto taskDto);

    // Task Discovery
    List<Task> findAvailableTasks();
    List<Task> findTasksByCategory(TaskCategory category);

    // Task Assignment
    Task assignTaskToTasker(Long taskId, User tasker);

    // Task Execution
    Task startTask(Long taskId, User tasker);
    Task submitTask(Long taskId, String submissionDetails);

    // Task Review
    Task reviewTask(Long taskId, boolean approved, double qualityScore);

    // Task Management
    Task cancelTask(Long taskId);
    void deleteTask(Long taskId);

    // Retrieval Methods
    Task getTaskById(Long taskId);
    List<Task> getTasksByClient(User client);
    List<Task> getTasksByTasker(User tasker);
}