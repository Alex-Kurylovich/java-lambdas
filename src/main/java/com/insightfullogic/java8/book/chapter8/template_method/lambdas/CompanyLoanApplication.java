package com.insightfullogic.java8.book.chapter8.template_method.lambdas;

// BEGIN CompanyLoanApplication
public class CompanyLoanApplication extends LoanApplication {

    public CompanyLoanApplication(Company company) {
        super(company::checkIdentity,
              company::checkHistoricalDebt,
              company::checkProfitAndLoss);
    }

}
// END CompanyLoanApplication
