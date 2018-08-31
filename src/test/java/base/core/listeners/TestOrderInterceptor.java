package base.core.listeners;

import amadeus.cars.automatron.carsbookingengine.whiteLabel.WhiteLabelPagesEnum;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.WhiteLabelTestScenario;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.List;

public class TestOrderInterceptor implements IMethodInterceptor {

    @Override
    @SuppressWarnings("all")
    /**
     * Factory Order interseptor for testNG tests
     * Add this class to @{@link org.testng.annotations.Listeners} annotation to work.
     * In @{@link org.testng.annotations.Factory} method wich return Objects array use test extends by {@link WhiteLabelTestScenario}
     * set priority to depended from {@link WhiteLabelPagesEnum} order value
     */
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

        for (int i = 0; i < methods.size(); i++) {
            IMethodInstance method = methods.get(i);
            if (method.getInstance() instanceof WhiteLabelTestScenario) {
                WhiteLabelTestScenario instance = (WhiteLabelTestScenario) method.getInstance();
                method.getMethod().setPriority(instance.getPageReference().order + instance.getPriority());
            }
        }

        methods.stream()
                .sorted((o1, o2) -> Integer.compare(o1.getMethod().getPriority(), o2.getMethod().getPriority()))
                .forEach(method -> {
                    System.out.println(String.format("|%s|%s|", method.getMethod().getPriority(), method.getMethod().getMethodName()));
                });
        return methods;
    }
}
