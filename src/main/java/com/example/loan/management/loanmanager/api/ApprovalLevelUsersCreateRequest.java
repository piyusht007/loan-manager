package com.example.loan.management.loanmanager.api;

import com.example.loan.management.loanmanager.model.LoanType;
import com.example.loan.management.loanmanager.model.User;

import java.util.List;

public class ApprovalLevelUsersCreateRequest {

    private LoanType loanType;
    private String levelName;
    private List<User> users;

    public ApprovalLevelUsersCreateRequest(final LoanType loanType,
                                           final String levelName,
                                           final List<User> users) {
        this.loanType = loanType;
        this.levelName = levelName;
        this.users = users;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public String getLevelName() {
        return levelName;
    }

    public List<User> getUsers() {
        return users;
    }
}
