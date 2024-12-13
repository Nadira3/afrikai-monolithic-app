package com.precious.AfrikAI.repository;

import org.springframework.stereotype.Repository;

import com.precious.AfrikAI.model.user.User;
import com.precious.AfrikAI.model.user.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    // Find users by specific roles
    List<User> findByRole(UserRole role);
}
