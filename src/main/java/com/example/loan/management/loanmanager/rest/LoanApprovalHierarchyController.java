package com.example.loan.management.loanmanager.rest;

import com.example.loan.management.loanmanager.api.LoanApprovalHierarchyCreateRequest;
import com.example.loan.management.loanmanager.api.LoanApprovalHierarchyUpdateRequest;
import com.example.loan.management.loanmanager.api.service.LoanApprovalHierarchyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan-management/loanApprovalHierarchy")
public class LoanApprovalHierarchyController implements LoanApprovalHierarchyService {

    @Autowired
    @Qualifier("loanApprovalHierarchyServiceImpl")
    private LoanApprovalHierarchyService loanApprovalHierarchyService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public void create(final @RequestBody LoanApprovalHierarchyCreateRequest loanApprovalHierarchyCreateRequest) {
        loanApprovalHierarchyService.create(loanApprovalHierarchyCreateRequest);
    }

    @Override
    public void deleteAll() {
        loanApprovalHierarchyService.deleteAll();
    }
}
