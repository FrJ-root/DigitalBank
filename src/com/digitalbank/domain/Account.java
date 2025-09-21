package com.digitalbank.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public class Account {
    private final Instant createdAt;
    private final String accountId;
    private final UUID ownerId;
    private BigDecimal balance;
    private Instant closedAt;
    private boolean active;

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    private final Map<String, Account> accounts = new HashMap<>();

    public Instant getCreatedAt() { return createdAt; }

    public String getAccountId() { return accountId; }

    public BigDecimal getBalance() { return balance; }

    public UUID getOwnerUserId() { return ownerId; }

    public boolean withdraw(BigDecimal amount) {
        if (this.balance.compareTo(amount) >= 0) {
            this.balance = this.balance.subtract(amount);
            return true;
        }
        return false;
    }

    public boolean isActive() {
        return active;
    }

    private String generateAccountId() {
        String randomPart = String.valueOf((int) (Math.random() * 9000 + 1000));
        String suffix = String.valueOf((int) (Math.random() * 9000 + 1000));
        return "BK-" + randomPart + "-" + suffix;
    }

    public Account(UUID ownerId) {
        this.ownerId = ownerId;
        this.accountId = generateAccountId();
        this.balance = BigDecimal.ZERO;
        this.createdAt = Instant.now();
        this.active = true;
    }

    public boolean deactivate() {
        if (!active) return false; // already inactive
        if (this.balance.compareTo(BigDecimal.ZERO) != 0) {
            return false; // cannot deactivate if balance is not 0
        }
        this.active = false;
        this.closedAt = Instant.now();
        return true;
    }

    public boolean activate() {
        if (active) return false; // already active
        this.active = true;
        this.closedAt = null;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s | Balance: %.2f | Status: %s",
                accountId, balance, active ? "Active" : "Inactive");
    }

}