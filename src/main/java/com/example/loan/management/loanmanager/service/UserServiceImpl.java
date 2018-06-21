package com.example.loan.management.loanmanager.service;

import com.example.loan.management.loanmanager.api.UserCreateRequest;
import com.example.loan.management.loanmanager.api.service.UserService;
import com.example.loan.management.loanmanager.dao.UserDao;
import com.example.loan.management.loanmanager.model.User;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UUID create(final UserCreateRequest userCreateRequest) {
        validateUserCreateRequest(userCreateRequest);

        final User user = mapToUser(userCreateRequest);
        final UUID userId = userDao.add(user);
        return userId;
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public List<User> getAll() {
        return userDao.getUsers();
    }

    private void validateUserCreateRequest(final UserCreateRequest userCreateRequest) {
        Preconditions.checkArgument(userCreateRequest != null, "User request cannot be null.");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userCreateRequest.getName()), "User name cannot be null/empty.");
        Preconditions.checkArgument(userCreateRequest.getRole() != null, "User role cannot be null.");
    }

    private User mapToUser(final UserCreateRequest userCreateRequest) {
        final UUID userId = UUID.randomUUID();
        return new User(userId, userCreateRequest.getName(), userCreateRequest.getRole());
    }
}
