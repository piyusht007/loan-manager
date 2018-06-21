package com.example.loan.management.loanmanager.api.service;

import com.example.loan.management.loanmanager.api.LoanApprovalHierarchyCreateRequest;
import com.example.loan.management.loanmanager.api.LoanApprovalHierarchyUpdateRequest;

public interface LoanApprovalHierarchyService {
    void create(LoanApprovalHierarchyCreateRequest loanApprovalHierarchyCreateRequest);

    void deleteAll();
}
