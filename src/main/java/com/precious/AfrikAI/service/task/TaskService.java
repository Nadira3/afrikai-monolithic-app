package com.precious.AfrikAI.service.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.precious.AfrikAI.dto.task.TaskCreationDto;
import com.precious.AfrikAI.model.task.Task;
import com.precious.AfrikAI.model.task.TaskCategory;
import com.precious.AfrikAI.model.task.TaskStatus;
import com.precious.AfrikAI.model.user.Client;
import com.precious.AfrikAI.model.user.User;
import com.precious.AfrikAI.repository.TaskRepository;
import com.precious.AfrikAI.repository.UserRepository;

@Service
@Transactional
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task createTask(Client client, TaskCreationDto taskDto) {
        Task newTask = new Task();
        newTask.setTitle(taskDto.getTitle());
        newTask.setDescription(taskDto.getDescription());
        newTask.setReward(taskDto.getReward());
        newTask.setCategory(taskDto.getCategory());
        newTask.setClient(client);
        newTask.setStatus(TaskStatus.CREATED);
        newTask.setCreatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(newTask);
        logger.info("New task created by client {}: {}", client.getUsername(), savedTask.getId());
        return savedTask;
    }

    @Override
    public List<Task> findAvailableTasks() {
        // TODO Auto-generated method stub
        return new ArrayList<Task>();
    }

    @Override
    public List<Task> findTasksByCategory(TaskCategory category) {
        // TODO Auto-generated method stub
        return new ArrayList<Task>();

    }

    @Override
    public Task assignTaskToTasker(Long taskId, User tasker) {
        // TODO Auto-generated method stub
        return new Task();
    }

    @Override
    public Task startTask(Long taskId, User tasker) {
        // TODO Auto-generated method stub
        return new Task();
    }

    @Override
    public Task submitTask(Long taskId, String submissionDetails) {
        // TODO Auto-generated method stub
        return new Task();
    }

    @Override
    public Task reviewTask(Long taskId, boolean approved, double qualityScore) {
        // TODO Auto-generated method stud
        return new Task();
    }

    @Override
    public Task cancelTask(Long taskId) {
        // TODO Auto-generated method stub
        return new Task();
    }

    @Override
    public void deleteTask(Long taskId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTask'");
    }

    @Override
    public Task getTaskById(Long taskId) {
        // TODO Auto-generated method stub
        return new Task();
    }

    @Override
    public List<Task> getTasksByClient(User client) {
        // TODO Auto-generated method stub
        return new ArrayList<Task>();
    }

    @Override
    public List<Task> getTasksByTasker(User tasker) {
        // TODO Auto-generated method stub
        return new ArrayList<Task>();
    }
}
