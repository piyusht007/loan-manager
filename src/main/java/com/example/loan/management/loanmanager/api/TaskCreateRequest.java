package com.example.loan.management.loanmanager.api;

import com.example.loan.management.loanmanager.model.Status;

import java.util.UUID;

public class TaskCreateRequest {

    private final Long loanApplicationId;
    private final UUID userId;
    private final String level;
    private final Status status;

    public TaskCreateRequest(final Long loanApplicationId,
                             final UUID userId,
                             final String level,
                             final Status status) {
        this.loanApplicationId = loanApplicationId;
        this.userId = userId;
        this.level = level;
        this.status = status;
    }

    public Long getLoanApplicationId() {
        return loanApplicationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }
}
