package com.example.loan.management.loanmanager.rest;

import com.example.loan.management.loanmanager.api.TaskCreateRequest;
import com.example.loan.management.loanmanager.api.service.TaskService;
import com.example.loan.management.loanmanager.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/loan-manager/tasks/")
public class TaskController implements TaskService {

    @Autowired
    @Qualifier("taskServiceImpl")
    private TaskService taskService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public UUID create(final @RequestBody TaskCreateRequest taskCreateRequest) {
        return taskService.create(taskCreateRequest);
    }

    @Override
    @RequestMapping(value = "/approve/{taskId}", method = RequestMethod.POST)
    public UUID approve(final @PathVariable UUID taskId) {
        return taskService.approve(taskId);
    }

    @Override
    @RequestMapping(value = "/needClarification/{taskId}", method = RequestMethod.POST)
    public UUID needClarification(final @PathVariable UUID taskId) {
        return taskService.needClarification(taskId);
    }

    @Override
    @RequestMapping(value = "/reject/{taskId}", method = RequestMethod.POST)
    public void reject(final @PathVariable UUID taskId) {
        taskService.reject(taskId);
    }

    @Override
    @RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
    public Task get(final @PathVariable UUID taskId) {
        return taskService.get(taskId);
    }

    @Override
    @RequestMapping(value = "/loanApplication/{loanApplicationId}", method = RequestMethod.GET)
    public Set<Task> getTasks(final @PathVariable Long loanApplicationId) {
        return taskService.getTasks(loanApplicationId);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public Set<Task> getAll() {
        return taskService.getAll();
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        taskService.deleteAll();
    }
}
