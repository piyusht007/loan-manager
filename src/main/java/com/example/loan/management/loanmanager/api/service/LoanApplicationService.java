package com.example.loan.management.loanmanager.api.service;

import com.example.loan.management.loanmanager.api.LoanApplicationRequest;
import com.example.loan.management.loanmanager.api.LoanApplicationResponse;

public interface LoanApplicationService {
    LoanApplicationResponse create(LoanApplicationRequest loanApplicationRequest);

    void deleteAll();
}
