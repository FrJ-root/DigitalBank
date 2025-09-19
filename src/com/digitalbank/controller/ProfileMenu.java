package com.digitalbank.controller;

import com.digitalbank.domain.User;
import com.digitalbank.utils.ConsoleClear;

import java.util.Scanner;

public class ProfileMenu {

    public static void showProfile(User user, Scanner scanner) {
        boolean e = true;
        while (e) {
            System.out.println("\n=== My Profile ===");
            System.out.println("1. ID: " + user.getId());
            System.out.println("2. Name: " + user.getName());
            System.out.println("3. Address: " + user.getAddress());
            System.out.println("4. Email: " + user.getEmail());
            System.out.println("5. Password: ********");
            System.out.println("0. Back");
            System.out.print("     | Choose : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("ID cannot be edited.");
                    break;
                case 2:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    user.setName(newName);
                    System.out.println("Name updated!");
                    break;
                case 3:
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine();
                    user.setAddress(newAddress);
                    System.out.println("Address updated!");
                    break;
                case 4:
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    user.setEmail(newEmail);
                    System.out.println("Email updated!");
                    break;
                case 5:
                    System.out.print("Enter new password (≥6 chars): ");
                    String newPwd = scanner.nextLine();
                    if (newPwd.length() >= 6) {
                        user.setPassword(newPwd);
                        System.out.println("Password updated!");
                    } else {
                        System.out.println("Password too short!");
                    }
                    break;
                case 0:
                    BankMenu.mainMenu(user);
                    break;
                default:
                    System.out.print("    Ooops! choose JUst from the menu");
            }
        }
    }
}