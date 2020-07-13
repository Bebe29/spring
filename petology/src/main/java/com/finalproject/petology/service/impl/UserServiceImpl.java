package com.finalproject.petology.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.finalproject.petology.dao.UserRepo;
import com.finalproject.petology.entity.User;
import com.finalproject.petology.service.UserService;
import com.finalproject.petology.util.EmailUtil;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    private PasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    @Autowired
    private EmailUtil emaiilUtil;

    @Override
    @Transactional
    public Iterable<User> getUsers() {
        return userRepo.findAll();
    };

    @Override
    @Transactional
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    };

    @Override
    @Transactional
    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    };

    @Override
    @Transactional
    public Optional<User> getUserById(int userId) {
        return userRepo.findById(userId);
    };

    @Override
    @Transactional
    public User loginUser(int userId, String password) {
        User findUser = userRepo.findById(userId).get();

        if (pwEncoder.matches(password, findUser.getPassword())) {
            return findUser;
        }
        return null;
    };

    @Override
    @Transactional
    public User registerUser(User user) {
        user.setId(0);
        String encodedPassword = pwEncoder.encode(user.getPassword());
        String verifyToken = pwEncoder.encode(user.getUsername() + user.getEmail());
        user.setPassword(encodedPassword);
        user.setVerified(false);
        user.setVerifyToken(verifyToken);

        User savedUser = userRepo.save(user);

        String linkToVerify = "http://localhost:3000/verify/" + user.getUsername() + "?token=" + verifyToken;
        String message = "<h1>Hello " + user.getFullName() + ",<h1>\n";
        message += "<h4>Welcome to Petology!<h4>\n";
        message += "<p>To activate your account please click the link below to verify:<p>\n";
        message += "<a href=\"" + linkToVerify + "\">Verify Account</a>";

        emaiilUtil.sendEmail(user.getEmail(), "Please verify your account", message);

        return savedUser;
    };

    @Override
    @Transactional
    public User verifyUser(String username, String token) {
        User findUser = userRepo.findByUsername(username).get();
        if (findUser.getVerifyToken().equals(token)) {
            findUser.setVerified(true);
        } else {
            throw new RuntimeException("Token is invalid");
        }
        userRepo.save(findUser);
        return findUser;
    }

    @Override
    @Transactional
    public User updateUserProfile(User user) {
        return userRepo.save(user);
    }

    @Override
    @Transactional
    public User changePassword(int userId, String oldPass, String newPass) {
        User findUser = userRepo.findById(userId).get();
        if (pwEncoder.matches(oldPass, findUser.getPassword())) {
            if (pwEncoder.matches(newPass, findUser.getPassword())) {
                return null;
            } else {
                String newEncodePassword = pwEncoder.encode(newPass);
                findUser.setPassword(newEncodePassword);
                return findUser;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public String forgotPassword(String email) {
        User findUser = userRepo.findByEmail(email).get();
        if (findUser == null)
            throw new RuntimeException("User with " + email + "doesn't exist");

        String passToken = pwEncoder.encode(findUser.getEmail() + findUser.getUsername());
        findUser.setPasswordToken(passToken);

        String linkToReset = "http://localhost:3000/forgot/" + findUser.getUsername() + "?token=" + passToken;
        String message = "<h1>Hello " + findUser.getFullName() + ",<h1>\n";
        message += "<h4>We received a request to reset your account password.<h4>\n";
        message += "<p>Click the link below to reset your password:<p>\n";
        message += "<a href=\"" + linkToReset + "\">Reset Your Password</a>";

        emaiilUtil.sendEmail(findUser.getEmail(), "Petology password reset link", message);
        return "Reset password link has been send";
    };

    @Override
    @Transactional
    public String resetPassword(String username, String token, String password) {
        User findUser = userRepo.findByUsername(username).get();
        if (findUser.getPasswordToken().equals(token)) {
            if (pwEncoder.matches(password, findUser.getPassword())) {
                return "Password already use";
            } else {
                String encodedPassword = pwEncoder.encode(password);
                findUser.setPassword(encodedPassword);
                System.out.println(findUser);
                return "Your password has been changed successfully";
            }
        }
        return null;
    }
}
