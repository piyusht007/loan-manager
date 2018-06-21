package com.example.loan.management.loanmanager.service;

import com.example.loan.management.loanmanager.api.TaskCreateRequest;
import com.example.loan.management.loanmanager.api.service.LoanService;
import com.example.loan.management.loanmanager.api.service.TaskService;
import com.example.loan.management.loanmanager.dao.ApprovalHierarchyDao;
import com.example.loan.management.loanmanager.dao.ApprovalLevelUsersDao;
import com.example.loan.management.loanmanager.dao.TaskDao;
import com.example.loan.management.loanmanager.model.*;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private ApprovalHierarchyDao approvalHierarchyDao;

    @Autowired
    private ApprovalLevelUsersDao approvalLevelUsersDao;

    @Autowired
    private LoanService loanService;

    @Override
    public UUID create(final TaskCreateRequest taskCreateRequest) {
        validateTaskCreateRequest(taskCreateRequest);

        final UUID taskId = UUID.randomUUID();
        final Task task = new Task(taskId,
                                   taskCreateRequest.getLoanApplicationId(),
                                   taskCreateRequest.getUserId(),
                                   taskCreateRequest.getLevel(),
                                   taskCreateRequest.getStatus());

        taskDao.add(task);
        return taskId;
    }

    @Override
    public UUID approve(final UUID taskId) {
        final Task existingTask = taskDao.getById(taskId);

        existingTask.setStatus(Status.APPROVED);
        taskDao.add(existingTask);

        if( approvalHierarchyDao.isLastLevel(LoanType.HOME_LOAN, existingTask.getLevel()) ) {
            loanService.create(existingTask.getLoanApplicationId());
            return existingTask.getId();
        } else {
            final Task newTask = createNewTaskForNextLevel(existingTask);

            taskDao.add(newTask);
            return newTask.getId();
        }
    }

    @Override
    public UUID needClarification(final UUID taskId) {
        final Task existingTask = taskDao.getById(taskId);

        existingTask.setStatus(Status.NEED_CLARIFICATION);
        taskDao.add(existingTask);

        if( approvalHierarchyDao.isFirstLevel(LoanType.HOME_LOAN, existingTask.getLevel()) ) {
            return existingTask.getId();
        } else {
            final Task newTask = createNewTaskForPreviousLevel(existingTask);

            taskDao.add(newTask);
            return newTask.getId();
        }
    }

    @Override
    public void reject(final UUID taskId) {
        final Task existingTask = taskDao.getById(taskId);

        existingTask.setStatus(Status.REJECTED);
        taskDao.add(existingTask);
    }

    @Override
    public void deleteAll() {
        taskDao.deleteAll();
    }

    @Override
    public Set<Task> getTasks(final Long loanApplicationId) {
        return taskDao.getTasksByLoanApplicationAId(loanApplicationId);
    }

    @Override
    public Task get(final UUID taskId) {
        return taskDao.getById(taskId);
    }

    @Override
    public Set<Task> getAll() {
        return taskDao.getAll();
    }

    private void validateTaskCreateRequest(final TaskCreateRequest taskCreateRequest) {
        Preconditions.checkArgument(taskCreateRequest != null, "Task create request cannot be null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(taskCreateRequest.getLevel()), "Task level cannot be null/empty");
        Preconditions.checkArgument(taskCreateRequest.getLoanApplicationId() != null, "Task loan application id cannot be null");
        Preconditions.checkArgument(taskCreateRequest.getUserId() != null, "Task user id cannot be null");
    }

    private Task createNewTaskForPreviousLevel(final Task task) {
        final String currentLevel = task.getLevel();
        final String previousLevel = getPreviousLevelFromApprovalHierarchy(currentLevel);
        final User user = getUserFromHierarchyLevelUserPool(previousLevel);

        return new Task(UUID.randomUUID(), task.getLoanApplicationId(), user.getId(), previousLevel, Status.NEED_CLARIFICATION);
    }

    private Task createNewTaskForNextLevel(final Task task) {
        final String currentLevel = task.getLevel();
        final String nextLevel = getNextLevelFromApprovalHierarchy(currentLevel);
        final User user = getUserFromHierarchyLevelUserPool(nextLevel);

        return new Task(UUID.randomUUID(), task.getLoanApplicationId(), user.getId(), nextLevel, Status.PENDING_APPROVAL);
    }

    private String getNextLevelFromApprovalHierarchy(final String currentLevel) {
        return approvalHierarchyDao.getLevelAfter(LoanType.HOME_LOAN, currentLevel);
    }

    private String getPreviousLevelFromApprovalHierarchy(final String currentLevel) {
        return approvalHierarchyDao.getLevelBefore(LoanType.HOME_LOAN, currentLevel);
    }

    private User getUserFromHierarchyLevelUserPool(final String level) {
        final ApprovalLevelUsers approvalLevelUsers = approvalLevelUsersDao.get(LoanType.HOME_LOAN, level);
        return approvalLevelUsers.getUsers().get(0);
    }
}
