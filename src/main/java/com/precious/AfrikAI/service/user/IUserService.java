package com.precious.AfrikAI.service.user;

import com.precious.AfrikAI.dto.user.UserCreationDto;
import com.precious.AfrikAI.dto.user.UserRegistrationDto;
import com.precious.AfrikAI.model.user.User;
import com.precious.AfrikAI.model.user.UserRole;

import java.util.List;

public interface IUserService {
    // User Registration
    User registerNewUser(UserRegistrationDto registrationDto);

    // User Creation
    User createUser(UserCreationDto creationDto);

    // User Management
    User getUserById(Long id);
    User updateUser(Long userId, User userDetails);
    void deleteUser(Long userId);
    List<User> getAllUsers();

    // Role Management
    void changeUserRole(Long userId, UserRole newRole);

    // Wallet Operations
    void addFundsToWallet(Long userId, double amount);
    void withdrawFundsFromWallet(Long userId, double amount);
}
