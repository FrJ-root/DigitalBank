package com.digitalbank.Interface;

import com.digitalbank.domain.User;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
}