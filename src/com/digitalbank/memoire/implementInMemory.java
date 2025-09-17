package com.digitalbank.memoire;

import com.digitalbank.domain.User;
import com.digitalbank.Interface.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class implementInMemory implements UserRepository {
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
