package com.digitalbank.app;

import com.digitalbank.repository.UserRepository;
import com.digitalbank.memory.InMemoryUserRepository;
import com.digitalbank.service.AuthService;
import com.digitalbank.domain.User;

import java.util.Scanner;

public class Main {
    public static UserRepository userRepository = new InMemoryUserRepository();
    public static AuthService authService = new AuthService(userRepository);
    public static Scanner scanner = new Scanner(System.in);

    public static void mainAuthenticatedMenu(User user) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu (Logged in as " + user.getEmail() + ") ===");
            System.out.println("1. Update profile");
            System.out.println("2. Change password");
            System.out.println("3. Create account");
            System.out.println("4. List my accounts");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Transfer");
            System.out.println("8. Transaction history");
            System.out.println("9. Close account");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
//                    authService.updateProfile(user);
                    break;
                case "2":
//                    authService.changePassword(user);
                    break;
                case "3":
//                    accountService.createAccount(user);
                    break;
                case "4":
//                    accountService.listAccounts(user);
                    break;
                case "5":
//                    transactionService.deposit(user);
                    break;
                case "6":
//                    transactionService.withdraw(user);
                    break;
                case "7":
//                    transactionService.transfer(user);
                    break;
                case "8":
//                    transactionService.history(user);
                    break;
                case "9":
//                    accountService.closeAccount(user);
                    break;
                case "0":
                    running = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }


    public static void loginPhase(){
        System.out.print("Enter Email: ");
        String loginEmail = scanner.nextLine();
        while (loginEmail.isEmpty()) {
            System.out.println("    !! Email cannot be empty.");
            System.out.print("Enter Email: ");
            loginEmail = scanner.nextLine();
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
            mainAuthenticatedMenu(loggedInUser);
        }
    }

    public static void registerPhase(){
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

    public static void welcomePhase(){
        System.out.println("=== Welcome to the Digital Bank ===");

        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    public static void menuPhase(){
        welcomePhase();
        System.out.println("|---");
        System.out.println("    |");
        System.out.println("     ->     Menu");

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit\n");
        System.out.print("   Choose : ");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                registerPhase();
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
//                System.out.println("1. Login");
//                System.out.println("0. Go to Menu\n");
//                System.out.print("   Choose: ");
//                int option2 = scanner.nextInt();
//                scanner.nextLine();
//                switch(option2){
//                    case 1:
//                        loginPhase();
//                        break;
//                    case 0:
//                        menuPhase();
//                        break;
//                }
                break;

            case 0:
                System.out.print("Goodbye!");
                break;

            default:
                System.out.print("    Ooops! choose JUst from the menu");
        }
    }

    public static void main(String[] args){
        menuPhase();
    }
}