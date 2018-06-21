package com.example.loan.management.loanmanager.api.service;

import com.example.loan.management.loanmanager.api.TaskCreateRequest;
import com.example.loan.management.loanmanager.model.Task;

import java.util.Set;
import java.util.UUID;

public interface TaskService {
    UUID create(TaskCreateRequest taskCreateRequest);

    UUID approve(UUID taskId);

    UUID needClarification(UUID taskId);

    void reject(UUID taskId);

    Task get(UUID taskId);

    Set<Task> getTasks(final Long loanApplicationId);

    Set<Task> getAll();

    void deleteAll();
}
