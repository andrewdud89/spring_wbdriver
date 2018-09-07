package base.core;


import amadeus.cars.automatron.core.assertion.AllureAssert;
import base.core.annotations.CarsTest;
import base.core.assertion.AllureSoftAssert;
import base.core.constants.Dir;
import base.core.convertor.Serializable;
import base.core.helpers.FReader;
import base.core.listeners.SuiteListener;
import base.core.listeners.TestNGFailureHandler;
import base.core.listeners.TestNGListener;
import base.core.listeners.TestNGSuccessHandler;
import base.core.utils.AllureUtils;
import base.core.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.util.JSONParseException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Non-UI Base Test class. Use for Rest Api/ Soap Test/ etc...
 */

@Listeners({TestNGListener.class, SuiteListener.class})
public abstract class TestScenario {

    private static final int SHUT_DOWN_TIME_SEC = 30;
    public static Logger log;
    private static Future whenMethod;
    private static ExecutorService service;
    protected AllureSoftAssert check;
    private List<TestNGFailureHandler> failHandlers;
    private List<TestNGSuccessHandler> successHandlers;
    private TestData testData;
    private ITestContext context;

    public TestScenario() {

        failHandlers = new ArrayList<>();
        successHandlers = new ArrayList<>();
        log = Logger.getLogger(this.getClass());

        System.setProperty("user.language", "en_GB");
        System.setProperty("file.encoding", "UTF-8");

        Locale.setDefault(Locale.ENGLISH);

    }

    /**
     * Add action for fail test condition
     *
     * @param handler {@link TestNGFailureHandler}
     */
    public void addFailHandler(TestNGFailureHandler handler) {
        if (context != null) {
            failHandlers.add(handler);
            context.setAttribute("failHandlers", failHandlers);
        } else {
            log.warn("ITestContext not set");
        }
    }

    /**
     * Add action for success test condition
     *
     * @param handler {@link TestNGSuccessHandler}
     */
    public void addSuccessHandler(TestNGSuccessHandler handler) {
        if (context != null) {
            successHandlers.add(handler);
            context.setAttribute("successHandlers", failHandlers);
        } else {
            log.warn("ITestContext not set");
        }
    }

    public void init(ITestContext context) {
        this.context = context;
        init();
        addFailHandler(this::onFailureAction);
    }

    /**
     * Test initialize.
     * this method read config file json (testDataFilename) and store it to {@link TestData} dto object (this.testData)
     */
    public void init() {
        if (context == null) {
            throw new NullPointerException("ITestContext is not set");
        }

        if (context.getAttribute("testData") != null) {
            this.testData = (TestData) context.getAttribute("testData");
            return;
        }

        String jenkinsEnvConfig = System.getenv(ConfigLoader.getInstance().getProperty("project.jenkins.data"));

        if (jenkinsEnvConfig != null) {
            this.testData = Serializable.deserialize(jenkinsEnvConfig, TestData.class);
        } else {
            CarsTest carsTest = getClass().getAnnotation(CarsTest.class);
            JSONObject config;
            if (carsTest != null) {
                if (carsTest.value().equals("default")) {
                    config = FReader.readJSON(Dir.TEST_DATA + "/default.json");
                } else {
                    config = FReader.readJSON(Dir.TEST_DATA + "/" + carsTest.value());
                }
            } else {
                config = FReader.readJSON(Dir.TEST_DATA + "/" + getClass().getName().replace(".", "\\") + ".json");
            }
            // if attachment array is present
            if (config.has("$attach")) {
                JSONArray arr = config.getJSONArray("$attach");
                //parse attachment values
                for (Object link : arr) {
                    String[] vars = StringUtils.replaceFromString((String) link, ".json").split("/");
                    // set json key
                    String key = vars[vars.length - 1];
                    if (!config.has(key)) {
                        //load json file
                        JSONObject item = (FReader.readJSON(Dir.TEST_DATA + "/" + link));
                        //add kv form file
                        config.put(key, item.get(key));
                    }
                }
                //remove attach array from object
                config.remove("$attach");
            }
            try {
                this.testData = Serializable.deserialize(config, TestData.class);
            } catch (JSONParseException e) {
                AllureAssert.fail("unable to parse testData json file", e);
            }
        }
        if (this.testData == null) {
            log.error("testData not set");
        }
        log.info(String.format("Test Config:\n%s\n", jsonPrettify(Serializable.serialize(this.testData))));
        AllureUtils.attachJSON(Serializable.serialize(this.testData), "config");
    }

    public ITestContext getContext() {
        return context;
    }

    /**
     * Execute then method, when BehaviorWhen is done
     */
    public void setContext(ITestContext context) {
        this.context = context;
    }

    /**
     * Default failure handler for TestScenario level
     *
     * @param result {@link ITestResult}
     */
    private void onFailureAction(ITestResult result) {
        AllureUtils.attachString(result.getThrowable().getMessage(), "Failure Message");
    }

    public TestData getTestData() {
        return testData;
    }

    public void setTestData(TestData testData) {
        this.testData = testData;
    }

    /**
     * Execute return, when done. (boolean = true)
     *
     * @param when boolean type callable method.
     * @return then condition
     */
    @SuppressWarnings("unchecked")
    public BehaviorThen when(Callable when) {

        service = Executors.newSingleThreadExecutor();
        whenMethod = service.submit(when);
        while (!whenMethod.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new BehaviorThen();
    }

    /**
     * Wait for boolean condition of callable method.
     */
    @SuppressWarnings("unchecked")
    protected void waitFor(Callable method) {
        ExecutorService localExecutor = Executors.newSingleThreadExecutor();
        Future localFuture = localExecutor.submit(method);
        int timer = 0;
        while (!localFuture.isDone() && timer < SHUT_DOWN_TIME_SEC) {
            try {
                timer++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        localExecutor.shutdown();
    }

    /**
     * prettify json serialize data
     *
     * @param object Serializable object
     */
    protected void printJSON(JSONObject object) {
        printJSON(object.toString());
    }

    protected void printJSON(String data) {
        System.out.println(jsonPrettify(data));
    }

    protected String jsonPrettify(String data) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(data);
        return gson.toJson(je);
    }

    protected String jsonPrettify(JSONObject data) {
        return jsonPrettify(data.toString());
    }

    public class BehaviorThen {


        public void then(Runnable then) {
            if (whenMethod != null && whenMethod.isDone()) {
                service.shutdown();
                whenMethod = null;
                then.run();
            }
        }
    }

}
