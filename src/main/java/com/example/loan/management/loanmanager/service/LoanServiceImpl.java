package com.example.loan.management.loanmanager.service;

import com.example.loan.management.loanmanager.api.service.LoanService;
import com.example.loan.management.loanmanager.dao.LoanApplicationDao;
import com.example.loan.management.loanmanager.dao.LoanDao;
import com.example.loan.management.loanmanager.model.Loan;
import com.example.loan.management.loanmanager.model.LoanApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanApplicationDao loanApplicationDao;

    @Autowired
    private LoanDao loanDao;

    @Override
    public UUID create(final Long loanApplicationId) {
        final LoanApplication loanApplication = loanApplicationDao.getById(loanApplicationId);

        final Loan loan = createLoanForProvidedLoanApplication(loanApplication);
        loanDao.add(loan);
        return loan.getLoanId();
    }

    @Override
    public Loan getByLoanApplicationId(final Long loanApplicationId) {
        return loanDao.getByLoanApplicationId(loanApplicationId);
    }

    private Loan createLoanForProvidedLoanApplication(final LoanApplication loanApplication) {
        final UUID loanId = UUID.randomUUID();
        return new Loan(loanId, loanApplication);
    }
}
