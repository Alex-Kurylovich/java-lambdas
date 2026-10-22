package com.insightfullogic.java8.book.chapter8.template_method.lambdas;

import com.insightfullogic.java8.book.chapter8.template_method.ApplicationDenied;

// BEGIN Criteria
public interface Criteria {

    void check() throws ApplicationDenied;

}
// END Criteria
