package com.example.loan.management.loanmanager.model;

import java.util.UUID;

public class Task {

    private UUID id;
    private Long loanApplicationId;
    private UUID userId;
    private String level;
    private Status status;

    public Task(final UUID id,
                final Long loanApplicationId,
                final UUID userId,
                final String level,
                final Status status) {
        this.id = id;
        this.loanApplicationId = loanApplicationId;
        this.userId = userId;
        this.level = level;
        this.status = status;
    }

    public UUID getId() {
        return id;
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

    public void setStatus(final Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if( this == o ) {
            return true;
        }
        if( o == null || getClass() != o.getClass() ) {
            return false;
        }

        final Task task = (Task) o;

        if( id != null ? !id.equals(task.id) : task.id != null ) {
            return false;
        }
        if( loanApplicationId != null ? !loanApplicationId.equals(task.loanApplicationId) : task.loanApplicationId != null ) {
            return false;
        }
        return userId != null ? userId.equals(task.userId) : task.userId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ( loanApplicationId != null ? loanApplicationId.hashCode() : 0 );
        result = 31 * result + ( userId != null ? userId.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("id='").append(id).append('\'');
        sb.append(", loanApplicationId=").append(loanApplicationId);
        sb.append(", userId=").append(userId);
        sb.append(", level='").append(level).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
