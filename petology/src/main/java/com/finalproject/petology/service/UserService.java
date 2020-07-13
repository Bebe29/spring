package com.finalproject.petology.service;

import java.util.Optional;

import com.finalproject.petology.entity.User;

public interface UserService {
    public Iterable<User> getUsers();

    public Optional<User> getUserByUsername(String username);

    public Optional<User> getUserByEmail(String email);

    public Optional<User> getUserById(int userId);

    public User loginUser(int userId, String password);

    public User registerUser(User user);

    public User verifyUser(String username, String token);

    public User updateUserProfile(User user);

    public User changePassword(int userId, String oldPass, String newPass);

    public String forgotPassword(String email);

    public String resetPassword(String username, String token, String password);
}
