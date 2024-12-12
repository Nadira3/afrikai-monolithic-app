package com.precious.AfrikAI.controller;

import com.precious.AfrikAI.dto.UserRegistrationDto;
import com.precious.AfrikAI.model.User;
import com.precious.AfrikAI.model.UserRole;
import com.precious.AfrikAI.service.user.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
        @Valid @RequestBody UserRegistrationDto registrationDto
    ) {
        User registeredUser = userService.registerNewUser(registrationDto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
        @PathVariable Long userId, 
        @RequestBody User userDetails
    ) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/role")
    public ResponseEntity<Void> changeUserRole(
        @PathVariable Long userId, 
        @RequestParam UserRole newRole
    ) {
        userService.changeUserRole(userId, newRole);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/wallet/add")
    public ResponseEntity<Void> addFundsToWallet(
        @PathVariable Long userId, 
        @RequestParam double amount
    ) {
        userService.addFundsToWallet(userId, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/wallet/withdraw")
    public ResponseEntity<Void> withdrawFundsFromWallet(
        @PathVariable Long userId, 
        @RequestParam double amount
    ) {
        userService.withdrawFundsFromWallet(userId, amount);
        return ResponseEntity.ok().build();
    }
}
