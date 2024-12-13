package com.precious.AfrikAI.model.task;

// Task Status Enum
public enum TaskStatus {
    CREATED,     // Task just created
    AVAILABLE,   // Task is open for taskers
    ASSIGNED,    // Task assigned to a tasker
    IN_PROGRESS, // Tasker is working on the task
    SUBMITTED,   // Tasker submitted the task
    UNDER_REVIEW,// Client is reviewing the submitted task
    COMPLETED,   // Task successfully completed
    REJECTED,    // Task submission rejected
    CANCELLED    // Task cancelled by client
}