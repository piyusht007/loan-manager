package com.example.loan.management.loanmanager.dao;

import com.example.loan.management.loanmanager.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDao {

    private final List<User> users = new ArrayList<>();

    public UUID add(final User user) {
        users.add(user);
        return user.getId();
    }

    public User getById(final UUID userId) {
        final Optional<User> optionalUser = users.stream().filter(user -> user.getId().equals(userId)).findFirst();

        return optionalUser.orElse(null);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void deleteAll() {
        users.clear();
    }
}
