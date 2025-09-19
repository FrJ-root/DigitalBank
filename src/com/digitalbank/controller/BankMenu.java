package com.digitalbank.controller;

import com.digitalbank.memory.InMemoryAccountRepo;
import com.digitalbank.memory.InMemoryUserRepo;
import com.digitalbank.repository.AccountRepo;
import com.digitalbank.repository.UserRepo;
import com.digitalbank.service.AccountService;
import com.digitalbank.service.AuthService;
import com.digitalbank.utils.ConsoleClear;
import com.digitalbank.domain.User;
import java.util.Scanner;

public class BankMenu {
    static Scanner scanner = new Scanner(System.in);
    static UserRepo userRepository = new InMemoryUserRepo();
    static AuthService authService = new AuthService(userRepository);
    static AccountRepo accountRepository = new InMemoryAccountRepo();
    static AccountService accountService = new AccountService(accountRepository);

    public static void mainMenu(User user) {
        ConsoleClear.clear();
        boolean p = true;
        while (p) {
            System.out.println("\n=== Main Menu (Logged in as " + user.getEmail() + ") ===");
            System.out.println("1. My profile");
            System.out.println("2. Bank Account");
            System.out.println("3. Transaction");
            System.out.println("6. Withdraw");
            System.out.println("7. Transfer");
            System.out.println("8. Deposit");
            System.out.println("0. Logout");
            System.out.print("     | Choose : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ConsoleClear.clear();
                    ProfileMenu.showProfile(user, scanner);
                    break;
                case 2:
                    ConsoleClear.clear();
                    AccountMenu accountMenu = new AccountMenu(accountService, scanner);
                    accountMenu.menuAccount(user);
                    break;
                case 3:
                    ConsoleClear.clear();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 0:
                    p = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("    Ooops! choose JUst from the menu");
            }
        }
    }
}