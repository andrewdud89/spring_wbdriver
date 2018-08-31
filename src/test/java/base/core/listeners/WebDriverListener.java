package base.core.listeners;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class WebDriverListener extends AbstractWebDriverEventListener {

    private static final Logger log = Logger.getLogger(WebDriverListener.class);
    private static WebDriverListener instance;
    WebDriver driver;
    private String oldUrl = null;
    private String currentUrl = null;
    private List<WebDriverOnUrlChangeHandler> onUrlChangeHandlers;
    private HashMap<WebElement, WebDriverAfterClickHandler> afterClickhandlers;

    public WebDriverListener(WebDriver driver) {
        this.driver = driver;
        this.oldUrl = driver.getCurrentUrl();

        onUrlChangeHandlers = new ArrayList<>();
        afterClickhandlers = new HashMap<>();

        instance = this;
    }

    public static WebDriverListener getInstance() {
        if (instance == null) {
            throw new NullPointerException("Listener not initialized. Call ChromeBrowser first.");
        }
        return instance;
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        checkUrlChange(url);
    }

    @Step("Navigate to {0}")
    public void urlChange(String url) {

        log.info(String.format("Navigate to %s", url));

        oldUrl = currentUrl;
        currentUrl = url;
        onUrlChangeHandlers.forEach(webDriverOnUrlChangeHandler -> webDriverOnUrlChangeHandler.onUrlChange(oldUrl, currentUrl));
    }

    public void checkUrlChange(String url) {
        if (!Objects.equals(currentUrl, url)) {
            urlChange(url);
        }
    }

    public void checkUrlChange() {
        checkUrlChange(driver.getCurrentUrl());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        afterClickhandlers.forEach((element1, afterClickHandler) -> {
            if (element.equals(element1))
                afterClickHandler.handle(element);
        });
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
    }

    public void addAfterClickHandler(WebElement element, WebDriverAfterClickHandler handler) {
        afterClickhandlers.put(element, handler);
    }

    public String getOldUrl() {
        return oldUrl;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void onUrlChangeHandler(WebDriverOnUrlChangeHandler handler) {
        this.onUrlChangeHandlers.add(handler);
    }
}
