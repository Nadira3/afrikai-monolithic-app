package com.precious.AfrikAI.model.user;

import java.util.ArrayList;
import java.util.List;

import com.precious.AfrikAI.model.task.Task;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("TASKER")
public class Tasker extends User {

    // List of tasks assigned to the Tasker
    @OneToMany(mappedBy = "assignedTasker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> assignedTasks = new ArrayList<>();

    // List of tasks completed by the Tasker
    @Column(name = "total_completed_tasks", nullable = false, columnDefinition = "integer default 0")
    private Integer totalCompletedTasks = 0;

    // Average rating of tasks completed by the Tasker
    @Column(name = "average_task_rating", nullable = false, columnDefinition = "double precision default 0.0")
    private Double averageTaskRating = 0.0;

    // Constructor for Tasker-specific creation
    public Tasker(String username, String email, String password, UserRole role) {
        super(username, email, password, role);
    }

}
