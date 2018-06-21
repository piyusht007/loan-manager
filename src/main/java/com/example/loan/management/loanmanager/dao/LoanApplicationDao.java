package com.example.loan.management.loanmanager.dao;

import com.example.loan.management.loanmanager.model.LoanApplication;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LoanApplicationDao {

    private final List<LoanApplication> loanApplications = new ArrayList<>();

    public Long add(final LoanApplication loanApplication) {
        loanApplications.add(loanApplication);
        return loanApplication.getApplicationId();
    }

    public LoanApplication getById(final Long applicationId) {
        final Optional<LoanApplication> loanApplicationOptional = loanApplications.stream()
                                                                .filter(e -> e.getApplicationId().equals(applicationId))
                                                                .findFirst();

        return loanApplicationOptional.orElse(null);
    }

    public List<LoanApplication> getAllLoanApplications() {
        return new ArrayList<>(loanApplications);
    }

    public void deleteAll() {
        loanApplications.clear();
    }
}
