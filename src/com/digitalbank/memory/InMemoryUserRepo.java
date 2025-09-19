package com.digitalbank.memory;

import com.digitalbank.domain.User;
import com.digitalbank.repository.UserRepo;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepo implements UserRepo {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }
}
