package com.example.loan.management.loanmanager.service;

import com.example.loan.management.loanmanager.api.LoanApprovalHierarchyCreateRequest;
import com.example.loan.management.loanmanager.api.LoanApprovalHierarchyUpdateRequest;
import com.example.loan.management.loanmanager.api.service.LoanApprovalHierarchyService;
import com.example.loan.management.loanmanager.dao.ApprovalHierarchyDao;
import com.example.loan.management.loanmanager.model.ApprovalHierarchy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApprovalHierarchyServiceImpl implements LoanApprovalHierarchyService {

    @Autowired
    private ApprovalHierarchyDao approvalHierarchyDao;

    @Override
    public void create(final LoanApprovalHierarchyCreateRequest loanApprovalHierarchyCreateRequest) {
        final ApprovalHierarchy approvalHierarchy = mapToApprovalHierarchy(loanApprovalHierarchyCreateRequest);

        approvalHierarchyDao.add(approvalHierarchy);
    }

    @Override
    public void deleteAll() {
        approvalHierarchyDao.deleteAll();
    }

    private ApprovalHierarchy mapToApprovalHierarchy(final LoanApprovalHierarchyCreateRequest loanApprovalHierarchyCreateRequest) {
        final ApprovalHierarchy approvalHierarchy = new ApprovalHierarchy(loanApprovalHierarchyCreateRequest.getLoanType(),
                                                                          loanApprovalHierarchyCreateRequest.getLevels());

        return approvalHierarchy;
    }
}
