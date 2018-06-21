package com.example.loan.management.loanmanager.api;

import com.example.loan.management.loanmanager.model.LoanType;

import java.util.List;

public class LoanApprovalHierarchyCreateRequest {
    private LoanType loanType;
    private List<String> levels;

    public LoanApprovalHierarchyCreateRequest() {

    }

    public LoanApprovalHierarchyCreateRequest(final LoanType loanType,
                                              final List levels) {
        this.loanType = loanType;
        this.levels = levels;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public List<String> getLevels() {
        return levels;
    }

    @Override
    public boolean equals(final Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        final LoanApprovalHierarchyCreateRequest that = (LoanApprovalHierarchyCreateRequest) o;

        if ( loanType != that.loanType ) {
            return false;
        }
        return levels != null ? levels.equals(that.levels) : that.levels == null;
    }

    @Override
    public int hashCode() {
        int result = loanType != null ? loanType.hashCode() : 0;
        result = 31 * result + ( levels != null ? levels.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoanApprovalHierarchyCreateRequest{");
        sb.append("loanType=").append(loanType);
        sb.append(", levels=").append(levels);
        sb.append('}');
        return sb.toString();
    }
}
