package com.precious.AfrikAI.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import com.precious.AfrikAI.model.UserRole;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private double wallet = 0.0;  // For tracking user earnings/balance

    // New enabled attribute
    @Column(nullable = false)
    private boolean enabled = true;  // Default to true when user is created

    @Column(nullable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();

    // Optional: Method to check if account is enabled
    public boolean isEnabled() {
        return this.enabled;
    }

    // Optional: Method to disable/enable account
    public void setAccountEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
