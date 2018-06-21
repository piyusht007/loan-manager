package com.example.loan.management.loanmanager.dao;

import com.example.loan.management.loanmanager.model.Task;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TaskDao {

    private final Set<Task> tasks = new HashSet<>();

    public UUID add(final Task task) {
        tasks.add(task);
        return task.getId();
    }

    public Task getById(final UUID taskId) {
        final Optional<Task> taskOptional = tasks.stream().filter(task -> task.getId().equals(taskId)).findFirst();

        return taskOptional.orElse(null);
    }

    public Set<Task> getTasksByLoanApplicationAId(final Long loanApplicationId) {
        final Set<Task> tasks = this.tasks.stream()
                                           .filter(task -> task.getLoanApplicationId().equals(loanApplicationId))
                                           .collect(Collectors.toSet());

        return tasks;
    }

    public Set<Task> getTasksByUserId(final UUID userId) {
        final Set<Task> tasks = this.tasks.stream().filter(task -> task.getUserId().equals(userId)).collect(Collectors.toSet());

        return tasks;
    }

    public void deleteAll() {
        tasks.clear();
    }

    public Set<Task> getAll() {
        return tasks;
    }
}
