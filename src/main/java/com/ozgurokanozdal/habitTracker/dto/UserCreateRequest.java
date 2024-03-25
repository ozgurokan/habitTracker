package com.ozgurokanozdal.habitTracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCreateRequest {

    @Size(min = 3)
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{6,24}$",
            message = "Username must be of 6 to 24 length with no special characters.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
                message = "Password must contain at least one uppercase, one lowercase, one number and one special symbol")
    private String password;

    @NotBlank
    @Email(message = "Invalid type of Email.")
    private String email;


    public UserCreateRequest() {
    }

    public UserCreateRequest(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
