package base.core.assertion;


import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Collection;
import java.util.Set;

public class AllureSoftAssert extends SoftAssert {

//    private final Map<AssertionError, IAssert<?>> collection = Maps.newLinkedHashMap();

    @Step("{1} : [Expected : true, Actual : {0}]")
    @Override
    public void assertTrue(boolean condition, String message) {
        super.assertTrue(condition, message);
    }

    @Step("{1} : [Expected : false, Actual : {0}]")
    @Override
    public void assertFalse(boolean condition, String message) {
        super.assertFalse(condition, message);
    }

    @Step("Fail : {0}")
    @Override
    public void fail(String message, Throwable realCause) {
        super.fail(message, realCause);
    }

    @Step("Fail : {0}")
    @Override
    public void fail(String message) {
        super.fail(message);
    }

//    @Step("{2} : [Expected : {1}, Actual : {0}]")
//    @Override
//    public <T> void assertEquals(T actual, T expected, String message) {
//        super.assertEquals(actual, expected, message);
//    }

    @Step("{2} : [Expected : {1}, Actual : {0}]")
    @Override
    public void assertEquals(String actual, String expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{3} : [Expected : {1}, Actual : {0}, Delta : {2}]")
    @Override
    public void assertEquals(double actual, double expected, double delta, String message) {
        super.assertEquals(actual, expected, delta, message);
    }

    @Step("{3} : [Expected : {1}, Actual : {0}, Delta : {2}]")
    @Override
    public void assertEquals(float actual, float expected, float delta, String message) {
        super.assertEquals(actual, expected, delta, message);
    }

    @Step("{2} : [Expected : {1}, Actual : {0}]")
    @Override
    public void assertEquals(long actual, long expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{2} : [Expected : {1}, Actual : {0}]")
    @Override
    public void assertEquals(boolean actual, boolean expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{2} : [Expected : {1}, Actual : {0}]")
    @Override
    public void assertEquals(byte actual, byte expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{2} : [Expected : {1}, Actual : {0}]")
    @Override
    public void assertEquals(char actual, char expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{2} : [Expected : {1}, Actual : {0}]")
    @Override
    public void assertEquals(short actual, short expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{2} : [expected : {1}, actual : {0}]")
    @Override
    public void assertEquals(int actual, int expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{1} : [Expected : not null, Actual : {0}]")
    @Override
    public void assertNotNull(Object object, String message) {
        super.assertNotNull(object, message);
    }

    @Step("{1} : [Expected : null, Actual : {0}]")
    @Override
    public void assertNull(Object object, String message) {
        super.assertNull(object, message);
    }

    @Step("{2}")
    @Override
    public void assertSame(Object actual, Object expected, String message) {
        super.assertSame(actual, expected, message);
    }

    @Step("{2}")
    @Override
    public void assertNotSame(Object actual, Object expected, String message) {
        super.assertNotSame(actual, expected, message);
    }

    @Step("{2} : [expected : {1}, actual : {0}]")
    @Override
    public void assertEquals(Collection<?> actual, Collection<?> expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{2}")
    @Override
    public void assertEquals(Object[] actual, Object[] expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{2}")
    @Override
    public void assertEqualsNoOrder(Object[] actual, Object[] expected, String message) {
        super.assertEqualsNoOrder(actual, expected, message);
    }

    @Step("{2}")
    @Override
    public void assertEquals(byte[] actual, byte[] expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{2}")
    @Override
    public void assertEquals(Set<?> actual, Set<?> expected, String message) {
        super.assertEquals(actual, expected, message);
    }

    @Step("{2}")
    @Override
    public void assertNotEquals(Object actual, Object expected, String message) {
        super.assertNotEquals(actual, expected, message);
    }

    @Step("{2}")
//    @Override
    public void assertNotEquals(String actual, String expected, String message) {
        super.assertNotEquals(actual, expected, message);
    }

    @Step("{2}")
//    @Override
    public void assertNotEquals(long actual, long expected, String message) {
        super.assertNotEquals(actual, expected, message);
    }

    @Step("{2} : [Expected : {1}, Actual : {0}]")
//    @Override
    public void assertNotEquals(boolean actual, boolean expected, String message) {
        super.assertNotEquals(actual, expected, message);
    }

    @Step("{2}")
//    @Override
    public void assertNotEquals(byte actual, byte expected, String message) {
        super.assertNotEquals(actual, expected, message);
    }

    @Step("{2}")
//    @Override
    public void assertNotEquals(char actual, char expected, String message) {
        super.assertNotEquals(actual, expected, message);
    }

    @Step("{2}")
//    @Override
    public void assertNotEquals(short actual, short expected, String message) {
        super.assertNotEquals(actual, expected, message);
    }

    @Step("{2}")
//    @Override
    public void assertNotEquals(int actual, int expected, String message) {
        super.assertNotEquals(actual, expected, message);
    }

    @Step("{2}")
    @Override
    public void assertNotEquals(float actual, float expected, float delta, String message) {
        super.assertNotEquals(actual, expected, delta, message);
    }

    @Step("{3}")
    @Override
    public void assertNotEquals(double actual, double expected, double delta, String message) {
        super.assertNotEquals(actual, expected, delta, message);
    }

//    @Override
//    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
//        super.onAssertFailure(assertCommand, ex);
//        Allure.LIFECYCLE.fire(new StepFailureEvent().withThrowable(ex));
//    }
}
