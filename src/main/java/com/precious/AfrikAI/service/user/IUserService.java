package com.precious.AfrikAI.service.user;

import com.precious.AfrikAI.dto.UserRegistrationDto;
import com.precious.AfrikAI.model.User;
import com.precious.AfrikAI.model.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    // User Registration
    User registerNewUser(UserRegistrationDto registrationDto);

    // User Management
    User getUserById(Long id);
    User updateUser(Long userId, User userDetails);
    void deleteUser(Long userId);

    // Role Management
    void changeUserRole(Long userId, UserRole newRole);

    // Wallet Operations
    void addFundsToWallet(Long userId, double amount);
    void withdrawFundsFromWallet(Long userId, double amount);
}
