package com.example.loan.management.loanmanager.service;

import com.example.loan.management.loanmanager.api.ApprovalLevelUsersCreateRequest;
import com.example.loan.management.loanmanager.api.service.ApprovalLevelUsersService;
import com.example.loan.management.loanmanager.dao.ApprovalLevelUsersDao;
import com.example.loan.management.loanmanager.model.ApprovalLevelUsers;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalLevelUsersServiceImpl implements ApprovalLevelUsersService {

    @Autowired
    private ApprovalLevelUsersDao approvalLevelUsersDao;

    @Override
    public void create(final ApprovalLevelUsersCreateRequest approvalLevelUsersCreateRequest) {
        validateApprovalLevelUsersCreateRequest(approvalLevelUsersCreateRequest);
        final ApprovalLevelUsers approvalLevelUsers = mapToApprovalLevelUsers(approvalLevelUsersCreateRequest);

        approvalLevelUsersDao.add(approvalLevelUsers);
    }

    private void validateApprovalLevelUsersCreateRequest(final ApprovalLevelUsersCreateRequest approvalLevelUsersCreateRequest) {
        Preconditions.checkArgument(approvalLevelUsersCreateRequest != null, "Approval level request cannot be null.");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(approvalLevelUsersCreateRequest.getLevelName()),
                                    "Approval level users level name cannot be null/empty.");
        Preconditions.checkArgument(approvalLevelUsersCreateRequest.getLoanType() != null,
                                    "Approval level users loan type cannot be null.");
        Preconditions.checkArgument(approvalLevelUsersCreateRequest.getUsers() != null
                                    && !approvalLevelUsersCreateRequest.getUsers().isEmpty(),
                                    "Approval level users request cannot be null/empty.");
    }

    private ApprovalLevelUsers mapToApprovalLevelUsers(final ApprovalLevelUsersCreateRequest approvalLevelUsersCreateRequest) {
        return new ApprovalLevelUsers(approvalLevelUsersCreateRequest.getLoanType(),
                                      approvalLevelUsersCreateRequest.getLevelName(),
                                      approvalLevelUsersCreateRequest.getUsers()

        );
    }
}
