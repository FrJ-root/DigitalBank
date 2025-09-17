package com.digitalbank.app;

import com.digitalbank.Interface.UserRepository;
import com.digitalbank.memoire.implementInMemory;
import com.digitalbank.service.AuthService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new implementInMemory();
        AuthService authService = new AuthService(userRepository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the Digital Bank ===");
        System.out.print("Your Email: ");
        String email = scanner.nextLine();
        System.out.print("Your Password: ");
        String password = scanner.nextLine();

        authService.register(email, password);
    }
}
