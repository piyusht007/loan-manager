package com.example.loan.management.loanmanager;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions
        (
                format = { "pretty","json:cucumber/test-report/SearchProject Reports/cucumber.json"},
                features ={ "classpath:features"},
                tags= {"@IntegrationTest"},
                glue = {"com.example.loan.management.loanmanager"}
        )
public class CucumberRunner {
}