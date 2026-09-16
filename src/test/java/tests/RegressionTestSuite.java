package tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        LoginTest.class,
        ProductTest.class,
        CartTest.class,
        CheckoutTest.class
})
public class RegressionTestSuite {
}