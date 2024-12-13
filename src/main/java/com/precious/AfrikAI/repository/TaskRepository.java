package com.precious.AfrikAI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.precious.AfrikAI.model.task.Task;
import com.precious.AfrikAI.model.task.TaskCategory;
import com.precious.AfrikAI.model.task.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByClientId(Long clientId);
    List<Task> findByAssignedTaskerId(Long taskerId);
    List<Task> findByCategory(TaskCategory category);
}