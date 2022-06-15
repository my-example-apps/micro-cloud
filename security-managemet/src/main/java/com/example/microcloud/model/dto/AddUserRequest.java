package com.example.microcloud.model.dto;

import com.example.microcloud.model.entity.User;

import javax.validation.constraints.NotEmpty;

public class AddUserRequest {
    public String username;
    public String nickname;
    public String password;
    public String confirmPassword;

    public String getUsername() {
        return username;
    }

    @NotEmpty
    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    @NotEmpty
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    @NotEmpty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    @NotEmpty
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User toUser() {
        User user = new User();
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setNickname(getNickname());
        return user;
    }
}
