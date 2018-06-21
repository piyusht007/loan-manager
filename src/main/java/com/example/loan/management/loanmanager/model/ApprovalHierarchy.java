package com.example.loan.management.loanmanager.model;

import java.util.List;

public class ApprovalHierarchy {

    private LoanType loanType;
    private List<String> levels;

    public ApprovalHierarchy(final LoanType loanType,
                             final List<String> levels) {
        this.loanType = loanType;
        this.levels = levels;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public List<String> getLevels() {
        return levels;
    }

    public void setLevels(final List<String> levels) {
        this.levels = levels;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApprovalHierarchy{");
        sb.append("loanType=").append(loanType);
        sb.append(", levels=").append(levels);
        sb.append('}');
        return sb.toString();
    }
}