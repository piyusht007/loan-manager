package com.example.loan.management.loanmanager.model;

import java.util.UUID;

public class Loan {

    private UUID loanId;
    private LoanApplication loanApplication;

    public Loan(final UUID loanId,
                final LoanApplication loanApplication) {
        this.loanId = loanId;
        this.loanApplication = loanApplication;
    }

    public UUID getLoanId() {
        return loanId;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Loan{");
        sb.append("loanId=").append(loanId);
        sb.append(", loanApplication=").append(loanApplication);
        sb.append('}');
        return sb.toString();
    }
}
