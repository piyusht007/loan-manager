package com.example.loan.management.loanmanager.api;

import java.util.UUID;

public class TaskApprovalRequest {
    private UUID taskId;

    public TaskApprovalRequest(final UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getTaskId() {
        return taskId;
    }
}
