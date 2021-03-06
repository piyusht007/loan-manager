@IntegrationTest

Feature: Submit Loan Application - Loan Application Approved - With Clarifications

  Scenario: Clean up the system
    Then delete all loan application, users, approval hierarchy, tasks..

  Scenario: Create home loan approval hierarchy
    Given the following approval levels
      | UNDERWRITING    |
      | RISK_ASSESSMENT |
      | FINANCE         |

  Scenario: Create users
    Given the following users and assign to approval hierarchy
      | name   | role            |
      | Sam    | UNDERWRITER     |
      | Peter  | UNDERWRITER     |
      | George | UNDERWRITER     |
      | Watson | RISK_OFFICER    |
      | Hugo   | RISK_OFFICER    |
      | Jason  | RISK_OFFICER    |
      | Frank  | FINANCE_MANAGER |
      | Simon  | FINANCE_MANAGER |
      | Tom    | FINANCE_MANAGER |

  Scenario Outline:
    When i submit the loan application of: <applicantName> for amount: <amount> and period: <tenure>
    And underwriter approves it
    And risk officer needs clarification on it
    And underwriter approves by providing clarifications on it
    And risk officer approves the clarified application
    And finance manager approves the clarified application
    Then <applicantName> should be disbursed

    Examples:
      | applicantName | amount | tenure |
      | Jack          | 20000  | 5      |
      | Jones         | 80000  | 3      |

  Scenario: Clean up the system
    Then delete all loan application, users, approval hierarchy, tasks..

