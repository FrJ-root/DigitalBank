package com.digitalbank.controller;

import java.util.Scanner;
import com.digitalbank.domain.User;
import com.digitalbank.utils.ConsoleClear;
import com.digitalbank.service.AccountService;

public class AccountMenu {
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountMenu(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    public void menuAccount(User user) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Account Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. List Accounts");
            System.out.println("3. Show Account");
            System.out.println("4. Balance Check");
            System.out.println("5. Status Account");
            System.out.println("0. Back");
            System.out.print("     | Choose : ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ConsoleClear.clear();
                    accountService.createAccount(user);
                    break;
                case 2:
                    ConsoleClear.clear();
                    accountService.listAccounts(user);
                    System.out.print("Press ENTER to go back...");
                    scanner.nextLine();
                    break;
                case 3:
                    ConsoleClear.clear();
                    System.out.print("Enter Account ID : ");
                    String showId = scanner.nextLine().trim();
                    accountService.showAccounts(user, showId);
                    break;
                case 4:
                    ConsoleClear.clear();
                    System.out.print("Enter Account ID: ");
                    String accId = scanner.nextLine().trim();
                    accountService.checkBalance(user, accId);
                    break;
                case 5:
                    ConsoleClear.clear();
                    System.out.print("Enter Account ID : ");
                    accId = scanner.nextLine().trim();
                    System.out.println("1. Active");
                    System.out.println("2. Deactivate");
                    System.out.println("0. Cancel");
                    System.out.print("     | Choose : ");
                    int statusChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (statusChoice) {
                        case 1:
                            accountService.setAccountStatus(user, accId, true);
                            break;
                        case 2:
                            accountService.setAccountStatus(user, accId, false);
                            break;
                        case 0:
                            ConsoleClear.clear();
                            menuAccount(user);
                            break;
                        default:
                            System.out.print("    Ooops! choose JUst from the menu");
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