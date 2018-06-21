package com.example.loan.management.loanmanager;

import com.example.loan.management.loanmanager.api.*;
import com.example.loan.management.loanmanager.api.service.*;
import com.example.loan.management.loanmanager.config.AppConfig;
import com.example.loan.management.loanmanager.model.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ContextConfiguration(classes = { AppConfig.class })
public class LoanApplicationSteps {

    private LoanApplicationRequest loanApplicationRequest = new LoanApplicationRequest();
    private LoanApplicationResponse loanApplicationResponse = new LoanApplicationResponse();

    private UUID taskId2 = null;
    private UUID taskId3 = null;
    private UUID taskId4 = null;
    private UUID taskId5 = null;

    @Autowired
    @Qualifier("loanApprovalHierarchyServiceImpl")
    private LoanApprovalHierarchyService loanApprovalHierarchyService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("loanApplicationServiceImpl")
    private LoanApplicationService loanApplicationService;

    @Autowired
    @Qualifier("taskServiceImpl")
    private TaskService taskService;

    @Autowired
    private ApprovalLevelUsersService approvalLevelUsersService;

    @Autowired
    private LoanService loanService;

    @Then("^delete all loan application, users, approval hierarchy, tasks\\.\\.$")
    public void delete_all_loan_application_users_approval_hierarchy_tasks() throws Throwable {
        loanApprovalHierarchyService.deleteAll();
        userService.deleteAll();
        loanApplicationService.deleteAll();
        taskService.deleteAll();
    }

    @Given("^the following approval levels$")
    public void the_following_approval_levels(List<String> approvalLevels) throws Throwable {
        final LoanApprovalHierarchyCreateRequest loanApprovalHierarchyCreateRequest = new LoanApprovalHierarchyCreateRequest(
                LoanType.HOME_LOAN,
                approvalLevels);

        loanApprovalHierarchyService.create(loanApprovalHierarchyCreateRequest);
    }

    @Given("^the following users and assign to approval hierarchy$")
    public void the_following_users(List<UserLine> userLines) throws Throwable {
        createUsers(userLines);
        assignUserToHierarchyLevels();
    }

    @When("^i submit the loan application of: (.*) for amount: (.*) and period: (.*)$")
    public void i_submit_the_loan_application_of_Jack_for_amount_and_period(String applicantName,
                                                                            String amount,
                                                                            String tenure) throws Throwable {
        loanApplicationRequest = new LoanApplicationRequest(applicantName, amount, tenure);
        loanApplicationResponse = loanApplicationService.create(loanApplicationRequest);
    }

    @When("^underwriter approves it$")
    public void underwriter_approves_it() throws Throwable {
        taskId2 = taskService.approve(loanApplicationResponse.getTaskId());
    }

    @When("^underwriter approves by providing clarifications on it$")
    public void underwriter_approves_by_providing_clarifications_on_it() throws Throwable {
        taskId4 = taskService.approve(taskId3);
    }

    @When("^risk officer approves it$")
    public void risk_officer_approves_it() throws Throwable {
        taskId3 = taskService.approve(taskId2);
    }

    @When("^risk officer rejects it$")
    public void risk_officer_rejects_it() throws Throwable {
        taskService.reject(taskId2);
    }

    @When("^risk officer needs clarification on it$")
    public void risk_officer_needs_clarification_on_it() throws Throwable {
        taskId3 = taskService.needClarification(taskId2);
    }

    @When("^risk officer approves the clarified application$")
    public void risk_officer_approves_the_clarified_application() throws Throwable {
        taskId5 = taskService.approve(taskId4);
    }

    @When("^finance manager approves it$")
    public void finance_manager_approves_it() throws Throwable {
        taskService.approve(taskId3);
    }

    @When("^finance manager approves the clarified application$")
    public void finance_manager_approves_the_clarified_application() throws Throwable {
        taskService.approve(taskId5);
    }

    @Then("^(.*) should be disbursed$")
    public void jack_should_be_disbursed(final String applicantName) throws Throwable {
        final Loan loan = loanService.getByLoanApplicationId(loanApplicationResponse.getLoanApplicationId());

        Assert.assertNotNull(loan);

        final LoanApplication loanApplication = loan.getLoanApplication();

        assertLoanDetails(applicantName, loanApplication);
    }

    @Then("^(.*) should not be disbursed$")
    public void jack_should_not_be_disbursed(final String applicantName) throws Throwable {
        final Loan loan = loanService.getByLoanApplicationId(loanApplicationResponse.getLoanApplicationId());

        Assert.assertNull(loan);

        final Set<Task> tasks = taskService.getTasks(loanApplicationResponse.getLoanApplicationId());

        assertRejectedTask(tasks);
    }

    private void assignUserToHierarchyLevels() {
        final List<User> users = userService.getAll();

        final List<User> underwriters = users.stream()
                                             .filter(userLine -> userLine.getRole().equals(Role.UNDERWRITER))
                                             .collect(Collectors.toList());
        final List<User> riskOfficers = users.stream()
                                             .filter(userLine -> userLine.getRole().equals(Role.RISK_OFFICER))
                                             .collect(Collectors.toList());
        final List<User> financeManagers = users.stream()
                                                .filter(userLine -> userLine.getRole().equals(Role.FINANCE_MANAGER))
                                                .collect(Collectors.toList());

        final ApprovalLevelUsersCreateRequest underwritingUsersCreateRequest = new ApprovalLevelUsersCreateRequest(LoanType.HOME_LOAN,
                                                                                                                   "UNDERWRITING",
                                                                                                                   underwriters);

        final ApprovalLevelUsersCreateRequest riskOfficersLevelUsersCreateRequest = new ApprovalLevelUsersCreateRequest(LoanType.HOME_LOAN,
                                                                                                                        "RISK_ASSESSMENT",
                                                                                                                        riskOfficers);

        final ApprovalLevelUsersCreateRequest financeManagerLevelUsersCreateRequest = new ApprovalLevelUsersCreateRequest(LoanType.HOME_LOAN,
                                                                                                                          "FINANCE",
                                                                                                                          financeManagers);

        approvalLevelUsersService.create(underwritingUsersCreateRequest);
        approvalLevelUsersService.create(riskOfficersLevelUsersCreateRequest);
        approvalLevelUsersService.create(financeManagerLevelUsersCreateRequest);
    }

    private void createUsers(final List<UserLine> userLines) {
        userLines.forEach(userLine -> {
            UserCreateRequest userCreateRequest = new UserCreateRequest(userLine.name, userLine.role);
            userService.create(userCreateRequest);
        });
    }

    private void assertRejectedTask(final Set<Task> tasks) {
        Assert.assertTrue(tasks.stream().anyMatch(task -> task.getStatus().equals(Status.REJECTED)));
    }

    private void assertLoanDetails(final String applicantName,
                                   final LoanApplication loanApplication) {
        Assert.assertNotNull(loanApplication);
        Assert.assertEquals(applicantName, loanApplication.getApplicantName());
        Assert.assertEquals(Integer.valueOf(loanApplicationRequest.getAmount()), loanApplication.getAmount());
        Assert.assertEquals(Double.valueOf(loanApplicationRequest.getTenure()), loanApplication.getTenure());
    }
}
