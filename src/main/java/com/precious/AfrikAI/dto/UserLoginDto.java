package com.precious.AfrikAI.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;


// User Login DTO
@Data
public class UserLoginDto {
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
