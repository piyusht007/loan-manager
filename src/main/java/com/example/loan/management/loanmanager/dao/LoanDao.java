package com.example.loan.management.loanmanager.dao;

import com.example.loan.management.loanmanager.model.Loan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LoanDao {

    private final List<Loan> loans = new ArrayList<>();

    public void add(final Loan loan) {
        loans.add(loan);
    }

    public Loan getByLoanApplicationId(final Long loanApplicationId) {
        final Optional<Loan> loanOptional = loans.stream()
                                                 .filter(loan -> loan.getLoanApplication()
                                                                     .getApplicationId()
                                                                     .equals(loanApplicationId))
                                                 .findFirst();

        return loanOptional.orElse(null);
    }
}
