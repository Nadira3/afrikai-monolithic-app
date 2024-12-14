package com.precious.AfrikAI.model.user;

import java.util.ArrayList;
import java.util.List;

import com.precious.AfrikAI.model.task.Task;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;



@Entity
@DiscriminatorValue("CLIENT")
@NoArgsConstructor
public class Client extends User {

    // List of tasks created by the client
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> createdTasks = new ArrayList<>();

    // Constructor
    public Client(String username, String email, String password, UserRole role) {
        super(username, email, password, role);
    }

}

