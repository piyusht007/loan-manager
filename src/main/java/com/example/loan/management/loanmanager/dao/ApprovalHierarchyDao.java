package com.example.loan.management.loanmanager.dao;

import com.example.loan.management.loanmanager.model.ApprovalHierarchy;
import com.example.loan.management.loanmanager.model.LoanType;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ApprovalHierarchyDao {

    private final List<ApprovalHierarchy> approvalHierarchies = new ArrayList<>();

    public void add(ApprovalHierarchy approvalHierarchy) {
        approvalHierarchies.add(approvalHierarchy);
    }

    public void deleteAll() {
        approvalHierarchies.clear();
    }

    public void addLevelAfter(final LoanType loanType,
                              final String existingLevel,
                              final String newLevel) {
        final ApprovalHierarchy approvalHierarchy = getByLoanType(loanType);
        final List<String> levels = approvalHierarchy.getLevels();

        levels.add(levels.indexOf(existingLevel) + 1, newLevel);
        approvalHierarchy.setLevels(levels);
    }

    public void addLevelBefore(final LoanType loanType,
                               final String existingLevel,
                               final String newLevel) {
        final ApprovalHierarchy approvalHierarchy = getByLoanType(loanType);
        final List<String> levels = approvalHierarchy.getLevels();

        levels.add(levels.indexOf(existingLevel) - 1, newLevel);
        approvalHierarchy.setLevels(levels);
    }

    public void addLevel(final LoanType loanType,
                         final String level) {
        final ApprovalHierarchy approvalHierarchy = getByLoanType(loanType);
        final List<String> levels = approvalHierarchy.getLevels();

        levels.add(level);
        approvalHierarchy.setLevels(levels);
    }

    public void removeLevel(final LoanType loanType,
                            final String level) {
        final ApprovalHierarchy approvalHierarchy = getByLoanType(loanType);
        final List<String> levels = approvalHierarchy.getLevels();

        levels.remove(level);
        approvalHierarchy.setLevels(levels);
    }

    public List<String> getLevels(final LoanType loanType) {
        final ApprovalHierarchy approvalHierarchy = getByLoanType(loanType);

        return approvalHierarchy.getLevels();
    }

    public String getLevelBefore(final LoanType loanType,
                                 final String existingLevel) {
        final ApprovalHierarchy approvalHierarchy = getByLoanType(loanType);
        final List<String> levels = approvalHierarchy.getLevels();

        return levels.get(levels.indexOf(existingLevel) - 1);
    }

    public String getLevelAfter(final LoanType loanType,
                                final String existingLevel) {
        final ApprovalHierarchy approvalHierarchy = getByLoanType(loanType);
        final List<String> levels = approvalHierarchy.getLevels();

        return levels.get(levels.indexOf(existingLevel) + 1);
    }

    public ApprovalHierarchy getByLoanType(final LoanType loanType) {
        final Optional<ApprovalHierarchy> approvalHierarchyOptional = approvalHierarchies.stream()
                                                                                         .filter(approvalHierarchy -> approvalHierarchy
                                                                                                 .getLoanType()
                                                                                                 .equals(loanType))
                                                                                         .findFirst();

        return approvalHierarchyOptional.orElse(null);
    }

    public boolean isLastLevel(final LoanType loanType, final String currentLevel) {
        final List<String> levels = fetchApprovalLevels(loanType);
        return levels.indexOf(currentLevel) == levels.size() - 1;
    }

    public boolean isFirstLevel(final LoanType loanType, final String currentLevel) {
        final List<String> levels = fetchApprovalLevels(loanType);
        return levels.indexOf(currentLevel) == 0;
    }

    private List<String> fetchApprovalLevels(final LoanType loanType) {
        final Optional<ApprovalHierarchy> approvalHierarchyOptional = approvalHierarchies.stream()
                                                                                         .filter(approvalHierarchy -> approvalHierarchy.getLoanType()
                                                                                                                                       .equals(loanType))
                                                                                         .findFirst();

        final ApprovalHierarchy approvalHierarchy = approvalHierarchyOptional.orElse(null);
        return approvalHierarchy == null ? Lists.newArrayList() : approvalHierarchy.getLevels();
    }
}
