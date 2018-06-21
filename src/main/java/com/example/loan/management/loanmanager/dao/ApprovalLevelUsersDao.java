package com.example.loan.management.loanmanager.dao;

import com.example.loan.management.loanmanager.model.ApprovalLevelUsers;
import com.example.loan.management.loanmanager.model.LoanType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ApprovalLevelUsersDao {

    private List<ApprovalLevelUsers> approvalLevelUsersList = new ArrayList<>();

    public void add(ApprovalLevelUsers approvalLevelUsers) {
        approvalLevelUsersList.add(approvalLevelUsers);
    }

    public ApprovalLevelUsers get(final LoanType loanType,
                                  final String level) {
        final Optional<ApprovalLevelUsers> approvalLevelUsersOptional = approvalLevelUsersList.stream()
                                                                                              .filter(approvalLevelUsers ->
                                                                                                              approvalLevelUsers
                                                                                                                      .getLoanType()
                                                                                                                      .equals(loanType)
                                                                                                              && approvalLevelUsers
                                                                                                                      .getLevelName()
                                                                                                                      .equals(level))
                                                                                              .findFirst();

        return approvalLevelUsersOptional.orElse(null);
    }
}
