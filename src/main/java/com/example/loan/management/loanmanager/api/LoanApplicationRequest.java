package com.example.loan.management.loanmanager.api;

public class LoanApplicationRequest {

    private String applicantName;
    private String amount;
    private String tenure;

    public LoanApplicationRequest(){

    }

    public LoanApplicationRequest(final String applicantName,
                                  final String amount,
                                  final String tenure) {
        this.applicantName = applicantName;
        this.amount = amount;
        this.tenure = tenure;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getAmount() {
        return amount;
    }

    public String getTenure() {
        return tenure;
    }

    @Override
    public boolean equals(final Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        final LoanApplicationRequest that = (LoanApplicationRequest) o;

        if ( applicantName != null ? !applicantName.equals(that.applicantName) : that.applicantName != null ) {
            return false;
        }
        if ( amount != null ? !amount.equals(that.amount) : that.amount != null ) {
            return false;
        }
        return tenure != null ? tenure.equals(that.tenure) : that.tenure == null;
    }

    @Override
    public int hashCode() {
        int result = applicantName != null ? applicantName.hashCode() : 0;
        result = 31 * result + ( amount != null ? amount.hashCode() : 0 );
        result = 31 * result + ( tenure != null ? tenure.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoanApplicationRequest{");
        sb.append("applicantName='").append(applicantName).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", tenure='").append(tenure).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
