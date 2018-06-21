package com.example.loan.management.loanmanager.rest;

import com.example.loan.management.loanmanager.api.LoanApplicationRequest;
import com.example.loan.management.loanmanager.api.LoanApplicationResponse;
import com.example.loan.management.loanmanager.api.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan-management/loanApplication")
public class LoanApplicationController implements LoanApplicationService {
    @Autowired
    @Qualifier("loanApplicationServiceImpl")
    private LoanApplicationService loanApplicationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public LoanApplicationResponse create(@RequestBody final LoanApplicationRequest loanApplicationRequest) {
        return loanApplicationService.create(loanApplicationRequest);
    }

    @Override
    public void deleteAll() {
        loanApplicationService.deleteAll();
    }
}
