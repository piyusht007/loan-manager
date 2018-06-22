package com.example.loan.management.loanmanager.service;

import com.example.loan.management.loanmanager.dao.ApprovalLevelUsersDao;
import com.example.loan.management.loanmanager.model.ApprovalLevelUsers;
import com.example.loan.management.loanmanager.model.LoanType;
import com.example.loan.management.loanmanager.model.User;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ApprovalLevelUserRetriever {

    private Map<String, Iterator<User>> levelToUserIterator = new ConcurrentHashMap<>();

    @Autowired
    private ApprovalLevelUsersDao approvalLevelUsersDao;

    public UUID retrieve(final String level) {
        final Iterator<User> userIterator = levelToUserIterator.get(level);

        if( userIterator == null ) {
            final ApprovalLevelUsers approvalLevelUsers = approvalLevelUsersDao.get(LoanType.HOME_LOAN, level);
            final List<User> users = approvalLevelUsers.getUsers();
            final Iterator<User> newUserIterator = Iterables.cycle(users).iterator();

            levelToUserIterator.put(level, newUserIterator);
            return newUserIterator.next().getId();
        } else {
            return userIterator.next().getId();
        }
    }
}
