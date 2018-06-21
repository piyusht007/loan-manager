package com.example.loan.management.loanmanager.model;

import java.util.List;

public class ApprovalLevelUsers {

    private LoanType loanType;
    private String levelName;
    private List<User> users;

    public ApprovalLevelUsers(final LoanType loanType,
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApprovalLevelUsers{");
        sb.append("loanType=").append(loanType);
        sb.append(", levelName='").append(levelName).append('\'');
        sb.append(", users=").append(users);
        sb.append('}');
        return sb.toString();
    }
}
