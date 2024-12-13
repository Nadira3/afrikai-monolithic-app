package com.precious.AfrikAI.model.user;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

import com.precious.AfrikAI.model.task.Task;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.EnumType;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    // Define the relationship for tasks associated with this user
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasksAsClient;

    @OneToMany(mappedBy = "assignedTasker")
    private List<Task> tasksAsAssignedTasker;


    private double wallet = 0.0;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();

    // Custom constructor for user creation (without auto-generated fields)
    public User(String username, String email, String password, UserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setEnabled() {
        this.enabled = false;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }

    public UserRole getRole() {
        return this.role;
    }
}
