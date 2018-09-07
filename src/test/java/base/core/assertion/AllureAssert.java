package base.core.assertion;

import base.core.utils.StringUtils;
import base.pages.WhiteLabelPagesEnum;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.collections.Lists;
import ru.yandex.qatools.allure.annotations.Step;

import java.lang.reflect.Array;
import java.util.*;

import static org.testng.internal.EclipseInterface.*;

public class AllureAssert extends Assert {


    /**
     * Protect constructor since it is a static only class
     */
    public AllureAssert() {
        // hide constructor
    }

    /**
     * Asserts that a condition is true. If it isn't,
     * an AssertionError, with the given message, is thrown.
     *
     * @param condition the condition to evaluate
     * @param message   the assertion error message
     */
    @Step("{1} : [Expected : true, Actual : {0}]")
    static public void assertTrue(boolean condition, String message) {
        if (!condition) {
            failNotEquals(condition, Boolean.TRUE, message);
        }
    }

    /**
     * Asserts that a condition is false. If it isn't,
     * an AssertionError, with the given message, is thrown.
     *
     * @param condition the condition to evaluate
     * @param message   the assertion error message
     */
    @Step("{1} : [Expected : false, Actual : {0}]")
    static public void assertFalse(boolean condition, String message) {
        if (condition) {
            failNotEquals(condition, Boolean.FALSE, message); // TESTNG-81
        }
    }


    /**
     * Fails a test with the given message and wrapping the original exception.
     *
     * @param message   the assertion error message
     * @param realCause the original exception
     */
    @Step("Fail: {0}")
    static public void fail(String message, Throwable realCause) {
        AssertionError ae = new AssertionError(message);
        ae.initCause(realCause);

        throw ae;
    }

    /**
     * Fails a test with the given message.
     *
     * @param message the assertion error message
     */
    @Step("Fail: {0}")
    static public void fail(String message) {
        throw new AssertionError(message);
    }

    /**
     * Asserts that two objects are equal. If they are not,
     * an AssertionError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    @Step("{2} : [Expected : {1}, Actual : {0}]")
    public static void assertEquals(Object actual, Object expected, String message) {
        if (expected != null && expected.getClass().isArray()) {
            assertArrayEquals(actual, expected, message);
            return;
        }
        assertEqualsImpl(actual, expected, message);
    }

    /**
     * Differs from {@link #assertEquals(Object, Object, String)} by not taking arrays into
     * special consideration hence comparing them by reference. Intended to be called directly
     * to test equality of collections content.
     */
    private static void assertEqualsImpl(Object actual, Object expected,
                                         String message) {
        if ((expected == null) && (actual == null)) {
            return;
        }
        if (expected == null ^ actual == null) {
            failNotEquals(actual, expected, message);
        }
        if (expected.equals(actual) && actual.equals(expected)) {
            return;
        }
        failNotEquals(actual, expected, message);
    }

    private static void assertArrayEquals(Object actual, Object expected, String message) {
        if (expected == actual) {
            return;
        }
        if (null == expected) {
            fail("expected a null array, but not null found. " + message);
        }
        if (null == actual) {
            fail("expected not null array, but null found. " + message);
        }
        //is called only when expected is an array
        if (actual.getClass().isArray()) {
            int expectedLength = Array.getLength(expected);
            if (expectedLength == Array.getLength(actual)) {
                for (int i = 0; i < expectedLength; i++) {
                    Object _actual = Array.get(actual, i);
                    Object _expected = Array.get(expected, i);
                    try {
                        assertEquals(_actual, _expected);
                    } catch (AssertionError ae) {
                        failNotEquals(actual, expected, message == null ? "" : message
                                + " (values at index " + i + " are not the same)");
                    }
                }
                //array values matched
                return;
            } else {
                failNotEquals(Array.getLength(actual), expectedLength, message == null ? "" : message
                        + " (Array lengths are not the same)");
            }
        }
        failNotEquals(actual, expected, message);
    }

    /**
     * Asserts that two Strings are equal. If they are not,
     * an AssertionError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    static public void assertEquals(String actual, String expected, String message) {
        assertEquals((Object) actual, (Object) expected, message);
    }

    /**
     * Asserts that two doubles are equal concerning a delta.  If they are not,
     * an AssertionError, with the given message, is thrown.  If the expected
     * value is infinity then the delta value is ignored.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param delta    the absolute tolerable difference between the actual and expected values
     * @param message  the assertion error message
     */
    @Step("{3}")
    static public void assertEquals(double actual, double expected, double delta, String message) {
        // handle infinity specially since subtracting to infinite values gives NaN and the
        // the following test fails
        if (Double.isInfinite(expected)) {
            if (!(expected == actual)) {
                failNotEquals(actual, expected, message);
            }
        } else if (Double.isNaN(expected)) {
            if (!Double.isNaN(actual)) {
                failNotEquals(actual, expected, message);
            }
        } else if (!(Math.abs(expected - actual) <= delta)) {
            failNotEquals(actual, expected, message);
        }
    }

    /**
     * Asserts that two floats are equal concerning a delta. If they are not,
     * an AssertionError, with the given message, is thrown.  If the expected
     * value is infinity then the delta value is ignored.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param delta    the absolute tolerable difference between the actual and expected values
     * @param message  the assertion error message
     */
    @Step("{3}")
    static public void assertEquals(float actual, float expected, float delta, String message) {
        // handle infinity specially since subtracting to infinite values gives NaN and the
        // the following test fails
        if (Float.isInfinite(expected)) {
            if (!(expected == actual)) {
                failNotEquals(actual, expected, message);
            }
        } else if (!(Math.abs(expected - actual) <= delta)) {
            failNotEquals(actual, expected, message);
        }
    }

    /**
     * Asserts that two longs are equal. If they are not,
     * an AssertionError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    static public void assertEquals(long actual, long expected, String message) {
        assertEquals(Long.valueOf(actual), Long.valueOf(expected), message);
    }

    /**
     * Asserts that two booleans are equal. If they are not,
     * an AssertionError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    static public void assertEquals(boolean actual, boolean expected, String message) {
        assertEquals(Boolean.valueOf(actual), Boolean.valueOf(expected), message);
    }

    /**
     * Asserts that two bytes are equal. If they are not,
     * an AssertionError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    static public void assertEquals(byte actual, byte expected, String message) {
        assertEquals(Byte.valueOf(actual), Byte.valueOf(expected), message);
    }

    /**
     * Asserts that two chars are equal. If they are not,
     * an AssertionFailedError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    static public void assertEquals(char actual, char expected, String message) {
        assertEquals(Character.valueOf(actual), Character.valueOf(expected), message);
    }

    /**
     * Asserts that two shorts are equal. If they are not,
     * an AssertionFailedError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    static public void assertEquals(short actual, short expected, String message) {
        assertEquals(Short.valueOf(actual), Short.valueOf(expected), message);
    }

    /**
     * Asserts that two ints are equal. If they are not,
     * an AssertionFailedError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    static public void assertEquals(int actual, int expected, String message) {
        assertEquals(Integer.valueOf(actual), Integer.valueOf(expected), message);
    }

    /**
     * Asserts that an object isn't null. If it is,
     * an AssertionFailedError, with the given message, is thrown.
     *
     * @param object  the assertion object
     * @param message the assertion error message
     */
    @Step("{1}: expected [not null], Actual [{0}]")
    static public void assertNotNull(Object object, String message) {
        if (object == null) {
            String formatted = "";
            if (message != null) {
                formatted = message + " ";
            }
            fail(formatted + "expected object to not be null");
        }
    }

    /**
     * Asserts that an object is null. If it is not,
     * an AssertionFailedError, with the given message, is thrown.
     *
     * @param object  the assertion object
     * @param message the assertion error message
     */
    @Step("{1}. expected [null]. Actual [{0}]")
    static public void assertNull(Object object, String message) {
        if (object != null) {
            failNotSame(object, null, message);
        }
    }

    /**
     * Asserts that two objects refer to the same object. If they do not,
     * an AssertionFailedError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    @Step("{2} expected [{1}]. Actual [{0}]")
    static public void assertSame(Object actual, Object expected, String message) {
        if (expected == actual) {
            return;
        }
        failNotSame(actual, expected, message);
    }

    /**
     * Asserts that two objects do not refer to the same objects. If they do,
     * an AssertionError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    @Step("{2}. [actual : {0}, expected : {1}]")
    static public void assertNotSame(Object actual, Object expected, String message) {
        if (expected == actual) {
            failSame(actual, expected, message);
        }
    }

    static private void failSame(Object actual, Object expected, String message) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }
        fail(formatted + ASSERT_LEFT2 + expected + ASSERT_MIDDLE + actual + ASSERT_RIGHT);
    }

    static private void failNotSame(Object actual, Object expected, String message) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }
        fail(formatted + ASSERT_LEFT + expected + ASSERT_MIDDLE + actual + ASSERT_RIGHT);
    }

    static private void failNotEquals(Object actual, Object expected, String message) {
        fail(format(actual, expected, message));
    }

    static String format(Object actual, Object expected, String message) {
        String formatted = "";
        if (null != message) {
            formatted = message + " ";
        }

        return formatted + ASSERT_LEFT + expected + ASSERT_MIDDLE + actual + ASSERT_RIGHT;
    }

    /**
     * Asserts that two collections contain the same elements in the same order. If they do not,
     * an AssertionError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    @Step("{2}. [actual : {0}, expected : {1}]")
    static public void assertEquals(Collection<?> actual, Collection<?> expected, String message) {
        if (actual == expected) {
            return;
        }

        if (actual == null || expected == null) {
            if (message != null) {
                fail(message);
            } else {
                fail("Collections not equal: expected: " + expected + " and actual: " + actual);
            }
        }

        assertEquals(actual.size(), expected.size(), (message == null ? "" : message + ": ") + "lists don't have the same size");

        Iterator<?> actIt = actual.iterator();
        Iterator<?> expIt = expected.iterator();
        int i = -1;
        while (actIt.hasNext() && expIt.hasNext()) {
            i++;
            Object e = expIt.next();
            Object a = actIt.next();
            String explanation = "Lists differ at element [" + i + "]: " + e + " != " + a;
            String errorMessage = message == null ? explanation : message + ": " + explanation;

            assertEqualsImpl(a, e, errorMessage);
        }
    }

    /**
     * Asserts that two iterators return the same elements in the same order. If they do not,
     * an AssertionError, with the given message, is thrown.
     * Please note that this assert iterates over the elements and modifies the state of the iterators.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    @Step("{2}. [actual : {0}, expected : {1}]")
    static public void assertEquals(Iterator<?> actual, Iterator<?> expected, String message) {
        if (actual == expected) {
            return;
        }

        if (actual == null || expected == null) {
            if (message != null) {
                fail(message);
            } else {
                fail("Iterators not equal: expected: " + expected + " and actual: " + actual);
            }
        }

        int i = -1;
        while (actual.hasNext() && expected.hasNext()) {

            i++;
            Object e = expected.next();
            Object a = actual.next();
            String explanation = "Iterators differ at element [" + i + "]: " + e + " != " + a;
            String errorMessage = message == null ? explanation : message + ": " + explanation;

            assertEqualsImpl(a, e, errorMessage);

        }

        if (actual.hasNext()) {

            String explanation = "Actual iterator returned more elements than the expected iterator.";
            String errorMessage = message == null ? explanation : message + ": " + explanation;
            fail(errorMessage);

        } else if (expected.hasNext()) {

            String explanation = "Expected iterator returned more elements than the actual iterator.";
            String errorMessage = message == null ? explanation : message + ": " + explanation;
            fail(errorMessage);

        }

    }

    /**
     * Asserts that two iterables return iterators with the same elements in the same order. If they do not,
     * an AssertionError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    @Step("{2}. [actual : {0}, expected : {1}]")
    static public void assertEquals(Iterable<?> actual, Iterable<?> expected, String message) {
        if (actual == expected) {
            return;
        }

        if (actual == null || expected == null) {
            if (message != null) {
                fail(message);
            } else {
                fail("Iterables not equal: expected: " + expected + " and actual: " + actual);
            }
        }

        Iterator<?> actIt = actual.iterator();
        Iterator<?> expIt = expected.iterator();

        assertEquals(actIt, expIt, message);
    }

    /**
     * Asserts that two arrays contain the same elements in the same order. If they do not,
     * an AssertionError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    @Step("{2}. [actual : {0}, expected : {1}]")
    static public void assertEquals(Object[] actual, Object[] expected, String message) {
        if (actual == expected) {
            return;
        }

        if ((actual == null && expected != null) || (actual != null && expected == null)) {
            if (message != null) {
                fail(message);
            } else {
                fail("Arrays not equal: " + Arrays.toString(expected) + " and " + Arrays.toString(actual));
            }
        }
        assertEquals(Arrays.asList(actual), Arrays.asList(expected), message);
    }

    /**
     * Asserts that two arrays contain the same elements in no particular order. If they do not,
     * an AssertionError, with the given message, is thrown.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the assertion error message
     */
    @Step("{2}. [actual : {0}, expected : {1}]")
    static public void assertEqualsNoOrder(Object[] actual, Object[] expected, String message) {
        if (actual == expected) {
            return;
        }

        if ((actual == null && expected != null) || (actual != null && expected == null)) {
            failAssertNoEqual(
                    "Arrays not equal: " + Arrays.toString(expected) + " and " + Arrays.toString(actual),
                    message);
        }

        if (actual.length != expected.length) {
            failAssertNoEqual(
                    "Arrays do not have the same size:" + actual.length + " != " + expected.length,
                    message);
        }

        List<Object> actualCollection = Lists.newArrayList();
        for (Object a : actual) {
            actualCollection.add(a);
        }
        for (Object o : expected) {
            actualCollection.remove(o);
        }
        if (actualCollection.size() != 0) {
            failAssertNoEqual(
                    "Arrays not equal: " + Arrays.toString(expected) + " and " + Arrays.toString(actual),
                    message);
        }
    }

    private static void failAssertNoEqual(String defaultMessage, String message) {
        if (message != null) {
            fail(message);
        } else {
            fail(defaultMessage);
        }
    }

    /**
     * Assert set equals
     */
    @Step("{2}. [actual : {0}, expected : {1}]")
    public static void assertEquals(Set<?> actual, Set<?> expected, String message) {
        if (actual == expected) {
            return;
        }

        if (actual == null || expected == null) {
            // Keep the back compatible
            if (message == null) {
                fail("Sets not equal: expected: " + expected + " and actual: " + actual);
            } else {
                failNotEquals(actual, expected, message);
            }
        }

        if (!actual.equals(expected)) {
            if (message == null) {
                fail("Sets differ: expected " + expected + " but got " + actual);
            } else {
                failNotEquals(actual, expected, message);
            }
        }
    }

    @Step("{2}. [actual : {0}, expected : {1}]")
    public static void assertEqualsDeep(Set<?> actual, Set<?> expected, String message) {
        if (actual == expected) {
            return;
        }

        if (actual == null || expected == null) {
            // Keep the back compatible
            if (message == null) {
                fail("Sets not equal: expected: " + expected + " and actual: " + actual);
            } else {
                failNotEquals(actual, expected, message);
            }
        }

        Iterator<?> actualIterator = actual.iterator();
        Iterator<?> expectedIterator = expected.iterator();
        while (expectedIterator.hasNext()) {
            Object expectedValue = expectedIterator.next();
            if (!actualIterator.hasNext()) {
                fail("Sets not equal: expected: " + expected + " and actual: " + actual);
            }
            Object value = actualIterator.next();
            if (expectedValue.getClass().isArray()) {
                assertArrayEquals(value, expectedValue, message);
            } else {
                assertEqualsImpl(value, expectedValue, message);
            }
        }
    }

    /**
     * Asserts that two maps are equal.
     */
    @Step("{2}. [actual : {0}, expected : {1}]")
    public static void assertEquals(Map<?, ?> actual, Map<?, ?> expected, String message) {
        if (actual == expected) {
            return;
        }

        if (actual == null || expected == null) {
            fail("Maps not equal: expected: " + expected + " and actual: " + actual);
        }

        if (actual.size() != expected.size()) {
            fail("Maps do not have the same size:" + actual.size() + " != " + expected.size());
        }

        Set<?> entrySet = actual.entrySet();
        for (Object anEntrySet : entrySet) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) anEntrySet;
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object expectedValue = expected.get(key);
            String assertMessage = message != null ? message : "Maps do not match for key:"
                    + key + " actual:" + value + " expected:" + expectedValue;
            assertEqualsImpl(value, expectedValue, assertMessage);
        }

    }

    @Step("{2}. [actual : {0}, expected : {1}]")
    public static void assertEqualsDeep(Map<?, ?> actual, Map<?, ?> expected, String message) {
        if (actual == expected) {
            return;
        }

        if (actual == null || expected == null) {
            fail("Maps not equal: expected: " + expected + " and actual: " + actual);
        }

        if (actual.size() != expected.size()) {
            fail("Maps do not have the same size:" + actual.size() + " != " + expected.size());
        }

        Set<?> entrySet = actual.entrySet();
        for (Object anEntrySet : entrySet) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) anEntrySet;
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object expectedValue = expected.get(key);
            String assertMessage = message != null ? message : "Maps do not match for key:"
                    + key + " actual:" + value + " expected:" + expectedValue;
            if (expectedValue.getClass().isArray()) {
                assertArrayEquals(value, expectedValue, assertMessage);
            } else {
                assertEqualsImpl(value, expectedValue, assertMessage);
            }
        }

    }

    /////
    // assertNotEquals
    //

    @Step("{2}. [actual : {0}, expected : {1}]")
    public static void assertNotEquals(Object actual1, Object actual2, String message) {
        boolean fail;
        try {
            Assert.assertEquals(actual1, actual2);
            fail = true;
        } catch (AssertionError e) {
            fail = false;
        }

        if (fail) {
            Assert.fail(message);
        }
    }

    static void assertNotEquals(String actual1, String actual2, String message) {
        assertNotEquals((Object) actual1, (Object) actual2, message);
    }

    static void assertNotEquals(String actual1, String actual2) {
        assertNotEquals(actual1, actual2, null);
    }

    static void assertNotEquals(long actual1, long actual2, String message) {
        assertNotEquals(Long.valueOf(actual1), Long.valueOf(actual2), message);
    }

    static void assertNotEquals(long actual1, long actual2) {
        assertNotEquals(actual1, actual2, null);
    }

    static void assertNotEquals(boolean actual1, boolean actual2, String message) {
        assertNotEquals(Boolean.valueOf(actual1), Boolean.valueOf(actual2), message);
    }

    static void assertNotEquals(boolean actual1, boolean actual2) {
        assertNotEquals(actual1, actual2, null);
    }

    static void assertNotEquals(byte actual1, byte actual2, String message) {
        assertNotEquals(Byte.valueOf(actual1), Byte.valueOf(actual2), message);
    }

    static void assertNotEquals(byte actual1, byte actual2) {
        assertNotEquals(actual1, actual2, null);
    }

    static void assertNotEquals(char actual1, char actual2, String message) {
        assertNotEquals(Character.valueOf(actual1), Character.valueOf(actual2), message);
    }

    static void assertNotEquals(char actual1, char actual2) {
        assertNotEquals(actual1, actual2, null);
    }

    static void assertNotEquals(short actual1, short actual2, String message) {
        assertNotEquals(Short.valueOf(actual1), Short.valueOf(actual2), message);
    }

    static void assertNotEquals(short actual1, short actual2) {
        assertNotEquals(actual1, actual2, null);
    }

    static void assertNotEquals(int actual1, int actual2, String message) {
        assertNotEquals(Integer.valueOf(actual1), Integer.valueOf(actual2), message);
    }

    static void assertNotEquals(int actual1, int actual2) {
        assertNotEquals(actual1, actual2, null);
    }

    @Step("{3}")
    static public void assertNotEquals(float actual1, float actual2, float delta, String message) {
        boolean fail;
        try {
            Assert.assertEquals(actual1, actual2, delta, message);
            fail = true;
        } catch (AssertionError e) {
            fail = false;
        }

        if (fail) {
            Assert.fail(message);
        }
    }

    @Step("{3}")
    static public void assertNotEquals(double actual1, double actual2, double delta, String message) {
        boolean fail;
        try {
            Assert.assertEquals(actual1, actual2, delta, message);
            fail = true;
        } catch (AssertionError e) {
            fail = false;
        }

        if (fail) {
            Assert.fail(message);
        }
    }


    @Step("{2}. [actual : {0}, expected : {1}]")
    public static void assertNotEquals(Set<?> actual, Set<?> expected, String message) {
        boolean fail;
        try {
            Assert.assertEquals(actual, expected, message);
            fail = true;
        } catch (AssertionError e) {
            fail = false;
        }

        if (fail) {
            Assert.fail(message);
        }
    }

    @Step("{2}. [actual : {0}, expected : {1}]")
    public static void assertNotEqualsDeep(Set<?> actual, Set<?> expected, String message) {
        boolean fail;
        try {
            Assert.assertEquals(actual, expected, message);
            fail = true;
        } catch (AssertionError e) {
            fail = false;
        }

        if (fail) {
            Assert.fail(message);
        }
    }

    @Step("{2}. [actual : {0}, expected : {1}]")
    public static void assertNotEquals(Map<?, ?> actual, Map<?, ?> expected, String message) {
        boolean fail;
        try {
            Assert.assertEquals(actual, expected, message);
            fail = true;
        } catch (AssertionError e) {
            fail = false;
        }

        if (fail) {
            Assert.fail(message);
        }
    }

    @Step("{2}. [actual : {0}, expected : {1}]")
    public static void assertNotEqualsDeep(Map<?, ?> actual, Map<?, ?> expected, String message) {
        boolean fail;
        try {
            Assert.assertEquals(actual, expected, message);
            fail = true;
        } catch (AssertionError e) {
            fail = false;
        }

        if (fail) {
            Assert.fail(message);
        }
    }

    public static void assertPage(EventFiringWebDriver driver, WhiteLabelPagesEnum whiteLabelPage, String message) {
        String url = driver.getCurrentUrl();
        String actual = StringUtils.getContainsValues(url, "search", "results", "booking", "confirmation");
        assertEquals(actual, whiteLabelPage.route, message);
    }
}
