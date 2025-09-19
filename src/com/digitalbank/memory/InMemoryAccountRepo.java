package com.digitalbank.memory;

import com.digitalbank.repository.AccountRepo;
import com.digitalbank.domain.Account;
import java.util.stream.Collectors;
import java.util.*;

public class InMemoryAccountRepo implements AccountRepo{
    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public void save(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    @Override
    public List<Account> findByUserId(UUID userId) {
        return accounts.values()
                .stream()
                .filter(acc -> acc.getOwnerUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Account findByAccountId(String accountId) {
        return accounts.get(accountId);
    }
}