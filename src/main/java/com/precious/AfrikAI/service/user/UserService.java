package com.precious.AfrikAI.service.user;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.precious.AfrikAI.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.precious.AfrikAI.dto.UserRegistrationDto;
import com.precious.AfrikAI.service.user.IUserService;
import com.precious.AfrikAI.model.User;
import com.precious.AfrikAI.model.UserRole;
import com.precious.AfrikAI.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(
        UserRepository userRepository, 
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUser(UserRegistrationDto registrationDto) {
        // Validate unique email and username
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            logger.warn("Attempted registration with existing email: {}", registrationDto.getEmail());
            throw new UserAlreadyExistsException("Email is already in use");
        }

        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            logger.warn("Attempted registration with existing username: {}", registrationDto.getUsername());
            throw new UserAlreadyExistsException("Username is already in use");
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setEmail(registrationDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        newUser.setRole(registrationDto.getRole());
        
        // Save and log
        User savedUser = userRepository.save(newUser);
        logger.info("New user registered: {} with role {}", savedUser.getUsername(), savedUser.getRole());
        
        return savedUser;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        User existingUser = getUserById(userId);
        
        // Update allowed fields
        existingUser.setUsername(userDetails.getUsername());
        
        logger.info("User updated: {}", existingUser.getUsername());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User userToDelete = getUserById(userId);
        userRepository.delete(userToDelete);
        logger.info("User deleted: {}", userId);
    }

    @Override
    public void changeUserRole(Long userId, UserRole newRole) {
        User user = getUserById(userId);
        user.setRole(newRole);
        userRepository.save(user);
        logger.info("User role changed: {} to {}", user.getUsername(), newRole);
    }

    // Wallet methods
    @Override
    public void addFundsToWallet(Long userId, double amount) {
        User user = getUserById(userId);
        if (amount > 0) {
            user.setWallet(user.getWallet() + amount);
            userRepository.save(user);
            logger.info("Added funds to user wallet: {} amount: {}", userId, amount);
        } else {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    @Override
    public void withdrawFundsFromWallet(Long userId, double amount) {
        User user = getUserById(userId);
        if (amount > 0 && user.getWallet() >= amount) {
            user.setWallet(user.getWallet() - amount);
            userRepository.save(user);
            logger.info("Withdrawn funds from user wallet: {} amount: {}", userId, amount);
        } else {
            throw new InsufficientFundsException("Insufficient funds or invalid amount");
        }
    }
}
