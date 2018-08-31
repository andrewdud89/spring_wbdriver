package base.core.listeners;

import org.testng.ITestResult;

public interface TestNGFailureHandler {

    void onFailure(ITestResult result);
}
