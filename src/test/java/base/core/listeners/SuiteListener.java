package base.core.listeners;

import amadeus.cars.automatron.core.annotations.ParallelTest;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class SuiteListener implements ISuiteListener {

    private ISuite suite;
    private Class<?> testClass;

    @Override
    public void onStart(ISuite suite) {

        this.suite = suite;
        if (suite.getAllMethods().size() == 0) {
            return;
        }
        this.testClass = suite.getAllMethods().get(0).getTestClass().getRealClass();

        ParallelTest parallel = testClass.getDeclaredAnnotation(ParallelTest.class);

        if (parallel != null) {
            System.out.println("ParallelTest annotation found");
            setParallelProperty(parallel);
        }
    }

    private void setParallelProperty(ParallelTest parallel) {

        suite.getXmlSuite().setParallel(parallel.parallel());
        suite.getXmlSuite().setThreadCount(parallel.threads());
        suite.getXmlSuite().setVerbose(parallel.verbose());

        for (Method classMethods : testClass.getMethods()) {
            DataProvider dp = classMethods.getAnnotation(DataProvider.class);
            if (dp != null) {
                suite.getXmlSuite().setDataProviderThreadCount(parallel.dataProviderThreadsCount());
                break;
            }
        }
    }

    @Override
    public void onFinish(ISuite suite) {
    }
}