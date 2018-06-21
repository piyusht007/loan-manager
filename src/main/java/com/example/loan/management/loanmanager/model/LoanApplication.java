package com.example.loan.management.loanmanager.model;

public class LoanApplication {
    private Long applicationId;
    private String applicantName;
    private Integer amount;
    private Double tenure;

    public LoanApplication(final Long applicationId,
                           final String applicantName,
                           final Integer amount,
                           final Double tenure) {
        this.applicationId = applicationId;
        this.applicantName = applicantName;
        this.amount = amount;
        this.tenure = tenure;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getTenure() {
        return tenure;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoanApplication{");
        sb.append("applicationId=").append(applicationId);
        sb.append(", amount=").append(amount);
        sb.append(", tenure=").append(tenure);
        sb.append(", applicantName='").append(applicantName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
