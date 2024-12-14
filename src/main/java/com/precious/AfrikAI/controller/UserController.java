package com.precious.AfrikAI.controller;

import com.precious.AfrikAI.dto.user.UserCreationDto;
import com.precious.AfrikAI.dto.user.UserRegistrationDto;
import com.precious.AfrikAI.exception.*;
import com.precious.AfrikAI.model.user.User;
import com.precious.AfrikAI.model.user.UserRole;
import com.precious.AfrikAI.service.user.UserService;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();  // Make sure this method exists in the UserService
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
        @Valid @RequestBody UserRegistrationDto registrationDto
    ) {
        try {
            User registeredUser = userService.registerUser(registrationDto);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUser(
        @RequestBody UserCreationDto creationDto
    ) {
        try {
            User createdUser = userService.createUser(creationDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (UserAlreadyExistsException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
            
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(
        @PathVariable Long userId,
        @RequestBody User userDetails
    ) {
    try {
        User updatedUser = userService.updateUser(userId, userDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    } catch (UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{userId}/role")
    public ResponseEntity<Void> changeUserRole(
        @PathVariable Long userId, 
        @RequestParam UserRole newRole
    ) {
        try {
            userService.changeUserRole(userId, newRole);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | UserNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{userId}/wallet/add")
    public ResponseEntity<Void> addFundsToWallet(
        @PathVariable Long userId, 
        @RequestParam double amount
    ) {
        try {
            userService.addFundsToWallet(userId, amount);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | UserNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{userId}/wallet/withdraw")
    public ResponseEntity<Void> withdrawFundsFromWallet(
        @PathVariable Long userId, 
        @RequestParam double amount
    ) {
        try {
            userService.withdrawFundsFromWallet(userId, amount);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | UserNotFoundException | InsufficientFundsException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
