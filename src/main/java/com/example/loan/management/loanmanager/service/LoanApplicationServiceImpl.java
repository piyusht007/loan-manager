package com.example.loan.management.loanmanager.service;

import com.example.loan.management.loanmanager.api.LoanApplicationRequest;
import com.example.loan.management.loanmanager.api.LoanApplicationResponse;
import com.example.loan.management.loanmanager.api.TaskCreateRequest;
import com.example.loan.management.loanmanager.api.service.LoanApplicationService;
import com.example.loan.management.loanmanager.api.service.TaskService;
import com.example.loan.management.loanmanager.dao.ApprovalHierarchyDao;
import com.example.loan.management.loanmanager.dao.ApprovalLevelUsersDao;
import com.example.loan.management.loanmanager.dao.LoanApplicationDao;
import com.example.loan.management.loanmanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private LoanApplicationDao loanApplicationDao;

    @Autowired
    @Qualifier("taskServiceImpl")
    private TaskService taskService;

    @Autowired
    private ApprovalHierarchyDao approvalHierarchyDao;

    @Autowired
    private ApprovalLevelUsersDao approvalLevelUsersDao;

    @Override
    public LoanApplicationResponse create(final LoanApplicationRequest loanApplicationRequest) {
        final LoanApplication loanApplication = mapToLoanApplication(loanApplicationRequest);
        final Long loanApplicationId = loanApplicationDao.add(loanApplication);
        final String firstLevel = getFirstLevelOfApprovalHierarchy();
        final User user = getUserFromGivenHierarchyUserPool(firstLevel);
        final TaskCreateRequest taskCreateRequest = prepareTaskCreateRequest(loanApplication.getApplicationId(),
                                                                             user.getId(),
                                                                             firstLevel,
                                                                             Status.PENDING_APPROVAL);
        final UUID taskId = taskService.create(taskCreateRequest);
        final LoanApplicationResponse loanApplicationResponse = new LoanApplicationResponse();

        loanApplicationResponse.setLoanApplicationId(loanApplicationId);
        loanApplicationResponse.setTaskId(taskId);
        return loanApplicationResponse;
    }

    private TaskCreateRequest prepareTaskCreateRequest(final Long applicationId,
                                                       final UUID id,
                                                       final String level,
                                                       final Status status) {
        return new TaskCreateRequest(applicationId, id, level, status);
    }

    @Override
    public void deleteAll() {
        loanApplicationDao.deleteAll();
    }

    private User getUserFromGivenHierarchyUserPool(final String firstLevel) {
        final ApprovalLevelUsers approvalLevelUsers = approvalLevelUsersDao.get(LoanType.HOME_LOAN, firstLevel);
        return approvalLevelUsers.getUsers().get(0);
    }

    private String getFirstLevelOfApprovalHierarchy() {
        final List<String> levels = approvalHierarchyDao.getLevels(LoanType.HOME_LOAN);
        return levels.get(0);
    }

    private LoanApplication mapToLoanApplication(final LoanApplicationRequest loanApplicationRequest) {
        final long applicationId = Math.abs(new Random().nextLong());
        return new LoanApplication(applicationId,
                                   loanApplicationRequest.getApplicantName(),
                                   Integer.parseInt(loanApplicationRequest.getAmount()),
                                   Double.valueOf(loanApplicationRequest.getTenure()));
    }
}
