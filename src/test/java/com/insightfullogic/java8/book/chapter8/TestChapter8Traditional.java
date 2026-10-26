package com.insightfullogic.java8.book.chapter8;

import com.insightfullogic.java8.book.chapter8.classes.ApplicationDenied;
import com.insightfullogic.java8.book.chapter8.classes.CompanyLoanApplication;
import com.insightfullogic.java8.book.chapter8.classes.EmployeeLoanApplication;
import com.insightfullogic.java8.book.chapter8.classes.PersonalLoanApplication;
import org.junit.Test;

public class TestChapter8Traditional {

    @Test
    public void fitsTogether() throws ApplicationDenied {
        new CompanyLoanApplication().checkLoanApplication();
        new PersonalLoanApplication().checkLoanApplication();
        new EmployeeLoanApplication().checkLoanApplication();
    }

}