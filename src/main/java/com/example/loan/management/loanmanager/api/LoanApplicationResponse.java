package com.example.loan.management.loanmanager.api;

import java.util.UUID;

public class LoanApplicationResponse {
    private Long loanApplicationId;
    private UUID taskId;

    public Long getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(final Long loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(final UUID taskId) {
        this.taskId = taskId;
    }
}
