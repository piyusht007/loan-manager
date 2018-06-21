package com.example.loan.management.loanmanager.api.service;

import com.example.loan.management.loanmanager.model.Loan;

import java.util.UUID;

public interface LoanService {
    UUID create(final Long loanApplicationId);

    Loan getByLoanApplicationId(Long loanApplicationId);
}
