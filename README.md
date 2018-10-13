# loan-manager
A loan application management solution. Its a spring boot application.

**Testing:**

Cucumber (Integration Tests) has been used to test the application scenarios.

**Running the application:**

Use following maven command to start the application:

`mvn spring-boot:run` (Execute the command on the parent folder i.e. **loan-manager**)

**Running integration tests:**

Use following maven command to run integration tests:

`mvn test -P cucumber-integration-tests`

We can see the test reports on following location: 
**cucumber/test-reports/index.html**

**UML Diagrams:**

**Class Diagram:** src/main/resources/class_diagrams

![](https://github.com/piyusht007/loan-manager/blob/master/src/main/resources/class_diagrams/loan_manager_class_diagram.png)

**Sequence Diagrams:** src/main/resources/sequence_diagrams 

![](https://github.com/piyusht007/loan-manager/blob/master/src/main/resources/sequence_diagrams/Existing_Loan_Application_Approval_Flow.png)

![](https://github.com/piyusht007/loan-manager/blob/master/src/main/resources/sequence_diagrams/Existing_Loan_Application_Need_Clarification_Flow.png)

![](https://github.com/piyusht007/loan-manager/blob/master/src/main/resources/sequence_diagrams/Existing_Loan_Application_Rejection_Flow.png)

![](https://github.com/piyusht007/loan-manager/blob/master/src/main/resources/sequence_diagrams/New_Loan_Application_Flow.png)

      





