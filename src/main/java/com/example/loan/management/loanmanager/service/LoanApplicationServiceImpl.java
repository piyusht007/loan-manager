package com.example.loan.management.loanmanager.service;

import com.example.loan.management.loanmanager.api.LoanApplicationRequest;
import com.example.loan.management.loanmanager.api.LoanApplicationResponse;
import com.example.loan.management.loanmanager.api.TaskCreateRequest;
import com.example.loan.management.loanmanager.api.service.LoanApplicationService;
import com.example.loan.management.loanmanager.api.service.TaskService;
import com.example.loan.management.loanmanager.dao.ApprovalHierarchyDao;
import com.example.loan.management.loanmanager.dao.LoanApplicationDao;
import com.example.loan.management.loanmanager.model.LoanApplication;
import com.example.loan.management.loanmanager.model.LoanType;
import com.example.loan.management.loanmanager.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
    private ApprovalLevelUserRetriever approvalLevelUserRetriever;

    @Override
    public LoanApplicationResponse create(final LoanApplicationRequest loanApplicationRequest) {
        final LoanApplication loanApplication = mapToLoanApplication(loanApplicationRequest);
        final Long loanApplicationId = loanApplicationDao.add(loanApplication);
        final String firstLevel = approvalHierarchyDao.getFirstLevelOfApprovalHierarchy(LoanType.HOME_LOAN);
        final UUID userId = approvalLevelUserRetriever.retrieve(firstLevel);
        final TaskCreateRequest taskCreateRequest = prepareTaskCreateRequest(loanApplication.getApplicationId(),
                                                                             userId,
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

    private LoanApplication mapToLoanApplication(final LoanApplicationRequest loanApplicationRequest) {
        final long applicationId = Math.abs(new Random().nextLong());
        return new LoanApplication(applicationId,
                                   loanApplicationRequest.getApplicantName(),
                                   Integer.parseInt(loanApplicationRequest.getAmount()),
                                   Double.valueOf(loanApplicationRequest.getTenure()));
    }
}
