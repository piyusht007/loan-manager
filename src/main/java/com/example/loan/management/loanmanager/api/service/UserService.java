package com.example.loan.management.loanmanager.api.service;

import com.example.loan.management.loanmanager.api.UserCreateRequest;
import com.example.loan.management.loanmanager.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UUID create(UserCreateRequest userCreateRequest);

    void deleteAll();

    List<User> getAll();
}
