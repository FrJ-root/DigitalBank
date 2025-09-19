package com.digitalbank.service;

import com.digitalbank.domain.Account;
import com.digitalbank.domain.User;
import com.digitalbank.repository.AccountRepo;
import com.digitalbank.utils.ConsoleClear;

import java.util.List;

import static com.digitalbank.app.Main.scanner;

public class AccountService {
    private final AccountRepo accountRepository;
    private boolean active;

    public AccountService(AccountRepo accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(User user) {
        Account account = new Account(user.getId());
        accountRepository.save(account);
        ConsoleClear.clear();
        System.out.println("         Your Account created: ");
        System.out.println("           | " + account.getAccountId()+" |\n\n\n");
        System.out.print("Press ENTER to back ...");
        scanner.nextLine();
        return account;
    }

    public List<Account> listAccounts(User user) {
        return accountRepository.findByUserId(user.getId());
    }

    public void showAccounts(User user, String accountId) {
        Account acc = accountRepository.findByAccountId(accountId);
        if (acc == null || !acc.getOwnerUserId().equals(user.getId())) {
            System.out.println("Account not found or you are not the owner.");
            return;
        }
        System.out.println("=== Account Details ===");
        System.out.println("Account ID: " + acc.getAccountId());
        System.out.println("Balance: " + acc.getBalance());
        System.out.println("Status: " + (acc.isActive() ? "Active" : "Inactive"));
        System.out.println("Created At: " + acc.getCreatedAt());
    }

    public void checkBalance(User user, String accountId) {
        Account acc = accountRepository.findByAccountId(accountId);
        if (acc == null || !acc.getOwnerUserId().equals(user.getId())) {
            System.out.println("Account not found.");
            return;
        }
        System.out.println("Balance for " + accountId + ": " + acc.getBalance());
    }

    public void setAccountStatus(User user, String accountId, boolean activate) {
        Account acc = accountRepository.findByAccountId(accountId);
        if (acc == null || !acc.getOwnerUserId().equals(user.getId())) {
            System.out.println("You are not the owner or account doesn't exist.");
            return;
        }

        if (activate) {
            if (acc.activate()) {
                System.out.println("Account activated successfully.");
            } else {
                System.out.println("Account is already active.");
            }
        } else {
            if (acc.deactivate()) {
                System.out.println("Account deactivated successfully.");
            } else {
                System.out.println("Cannot deactivate account. Balance must be 0 or account already inactive.");
            }
        }
    }
}