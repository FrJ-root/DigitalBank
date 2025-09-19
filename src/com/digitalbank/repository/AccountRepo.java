package com.digitalbank.repository;

import com.digitalbank.domain.Account;
import java.util.List;
import java.util.UUID;

public interface AccountRepo{
    void save(Account account);
    List<Account> findByUserId(UUID userId);
    Account findByAccountId(String accountId);
}