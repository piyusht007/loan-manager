package com.example.loan.management.loanmanager.rest;

import com.example.loan.management.loanmanager.api.UserCreateRequest;
import com.example.loan.management.loanmanager.api.service.UserService;
import com.example.loan.management.loanmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loan-management/user")
public class UserController implements UserService {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public UUID create(@RequestBody final UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }

    @Override
    public void deleteAll() {
        userService.deleteAll();
    }

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }
}
