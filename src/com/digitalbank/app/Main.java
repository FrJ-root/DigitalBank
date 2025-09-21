package com.digitalbank.app;

import com.digitalbank.memory.InMemoryAccountRepo;
import com.digitalbank.memory.InMemoryUserRepo;
import com.digitalbank.service.AccountService;
import com.digitalbank.controller.AccountMenu;
import com.digitalbank.controller.BankMenu;
import com.digitalbank.repository.UserRepo;
import com.digitalbank.service.AuthService;
import com.digitalbank.utils.ConsoleClear;
import com.digitalbank.domain.User;
import java.util.Scanner;

public class Main {
    public static AccountService accountService = new AccountService(new InMemoryAccountRepo());
    public static Scanner scanner = new Scanner(System.in);
    public static AccountMenu accountMenu = new AccountMenu(accountService, scanner);
    public static UserRepo userRepository = new InMemoryUserRepo();
    public static AuthService authService = new AuthService(userRepository);

    public static void Effect(String text, int retard) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                Thread.sleep(retard);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void registerPhase(){
        ConsoleClear.clear();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty.");
            System.out.print("Enter Name: ");
            name = scanner.nextLine();
        }

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        while (address.isEmpty()) {
            System.out.print("Address cannot be empty.");
            System.out.print("Enter Address: ");
            address = scanner.nextLine();
        }

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        while (!email.contains("@") || !email.contains(".")) {
            System.out.print("     !! Invalid email cs it not like this 'email@domaine.com' be aware bro ^_-\n");
            System.out.print("Enter a valid email: ");
            email = scanner.nextLine();
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        while (password.length() < 6) {
            System.out.print("     !! Password must be at least 6 chars ^_-\n");
            System.out.print("Enter a valid pass: ");
            password = scanner.nextLine();
        }
        authService.register(name, address, email, password);
    }

    public static void welcomePhase() {
        ConsoleClear.clear();
        System.out.println("   === *********************** ===");
        String[] startApp = {
                "   === DIGITAL BANKING PROGRAM ===",
        };
        for (String line : startApp) {
            Effect(line, 50);
            System.out.println();
        }
        System.out.println("   === *********************** ===\n\n\n");
        System.out.print(" | Press Enter to continue...");
        scanner.nextLine();
    }

    public static void loginPhase() {
        ConsoleClear.clear();
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter Email: ");
            String loginEmail = scanner.nextLine().trim();
            while (loginEmail.isEmpty()) {
                System.out.println("    !! Email cannot be empty.");
                System.out.print("Enter Email: ");
                loginEmail = scanner.nextLine().trim();
            }

            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();
            while (loginPassword.isEmpty()) {
                System.out.println("    !! Password cannot be empty.");
                System.out.print("Enter Password: ");
                loginPassword = scanner.nextLine();
            }

            User loggedInUser = authService.login(loginEmail, loginPassword);
            if (loggedInUser != null) {
                BankMenu.mainMenu(loggedInUser);
                return;
            }

            attempts++;
            System.out.println("    !! Bad credentials, try again (" + (MAX_ATTEMPTS - attempts) + " attempts left)\n");
        }

        System.out.println("    !! Too many failed attempts.");
        System.out.print("       | Press Enter to Back...");
        scanner.nextLine();
        menuPhase();
    }

    public static void menuPhase(){
        ConsoleClear.clear();
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit\n");
        System.out.print("   Choose : ");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                registerPhase();
                ConsoleClear.clear();
                System.out.println("1. Login");
                System.out.println("0. Go to Menu\n");
                System.out.print("   Choose: ");
                int option2 = scanner.nextInt();
                scanner.nextLine();
                switch(option2){
                    case 1:
                        loginPhase();
                        break;
                    case 0:
                        menuPhase();
                        break;
                }
                break;
            case 2:
                loginPhase();
                break;
            case 0:
                System.out.print("Goodbye!");
                break;
            default:
                System.out.print("    Ooops! choose JUst from the menu");
        }
    }

    public static void main(String[] args){
        welcomePhase();
        menuPhase();
    }
}