package com.digitalbank.repository;

import com.digitalbank.domain.User;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
}