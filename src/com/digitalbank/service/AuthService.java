package com.digitalbank.service;

import com.digitalbank.domain.User;
import com.digitalbank.repository.UserRepo;
import com.digitalbank.utils.PasswordHasher;
import com.digitalbank.validation.UserValidator;

import java.util.Optional;

public class AuthService {

    private final UserRepo userRepository;

    public AuthService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String name, String address, String email, String password) {

        if (!UserValidator.isValidEmail(email)) {
            System.out.println("Invalid email format");
            return;
        }

        if (!UserValidator.isValidPassword(password)) {
            System.out.println("Password must be at least 6 characters");
            return;
        }

        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("Email already used, choose another one");
            return;
        }

        User user = new User(name, address, email, password);
        userRepository.save(user);
        System.out.println("Register successful");
    }

    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            System.out.println("No user found with this email");
            return null;
        }

        User user = userOpt.get();
        String hashedInput = PasswordHasher.hash(password);
        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password");
            return null;
        }
        return user;
    }
}
