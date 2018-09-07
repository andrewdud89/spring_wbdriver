package base.core.listeners;


import org.apache.log4j.Logger;
import org.testng.*;

import java.util.List;

/**
 * Listener for TestNG TestRunner. By default appended to {@link TestScenario}
 * Created by zhitnikov on 6/23/2017.
 */
public class TestNGListener extends TestListenerAdapter implements IRetryAnalyzer, ITestListener {

    private static Logger log;
    private static TestNGListener instance;

    public TestNGListener() {
        instance = this;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(String.format("\033[1;33m[TEST][START]\t:::\t%s\033[0m", iTestResult.getMethod().getMethodName()));
    }

    @Override
    @SuppressWarnings("all")
    public void onTestSuccess(ITestResult iTestResult) {
        ITestNGMethod successMethod = iTestResult.getMethod();
        log.info(String.format("\033[1;32m[TEST][PASS]\t:::\t%s\033[0m", successMethod.getMethodName()));
        List<TestNGSuccessHandler> handlers = (List<TestNGSuccessHandler>) iTestResult.getTestContext().getAttribute("successHandlers");
        if (handlers != null)
            handlers.forEach(successHandler -> successHandler.onSuccess(iTestResult));
    }

    @Override
    @SuppressWarnings("all")
    public void onTestFailure(ITestResult iTestResult) {
        ITestNGMethod testNGMethod = iTestResult.getMethod();
        log.error(String.format("\033[1;31m[TEST][FAIL]\t:::\t%s\033[0m", testNGMethod.getMethodName()));
        List<TestNGFailureHandler> handlers = (List<TestNGFailureHandler>) iTestResult.getTestContext().getAttribute("failHandlers");
        if (handlers != null)
            handlers.forEach(failureHandler -> failureHandler.onFailure(iTestResult));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        super.onTestSkipped(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        super.onTestFailedButWithinSuccessPercentage(iTestResult);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        if (iTestContext.getSuite().getAllMethods().size() == 0) {
            return;
        }
        log = Logger.getLogger(iTestContext.getSuite().getAllMethods().get(0).getRealClass());
        log.info(String.format("\033[1;36m[SUITE][START]\t:::\t%s\033[0m", iTestContext.getName()));

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        log.info(String.format("\033[1;36m[SUITE][DONE]\t:::\t%s\033[0m", iTestContext.getName()));
    }

    @Override
    public boolean retry(ITestResult iTestResult) {
        return false;
    }

}
