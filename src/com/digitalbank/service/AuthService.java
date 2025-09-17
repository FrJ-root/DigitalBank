package com.digitalbank.service;

import com.digitalbank.domain.User;
import com.digitalbank.Interface.UserRepository;

public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("Enter another email");
            return;
        }

        if (password.length() < 6) {
            System.out.println("make the password more than 6 characters");
            return;
        }

        User user = new User(email, password);
        userRepository.save(user);
        System.out.println("Register succes ^_- " + email);
    }
}
