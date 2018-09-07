package base.pages;

import base.core.listeners.WebDriverListener;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public abstract class WebPage {

    static final int DEFAULT_EXPLICIT_WAIT = 30;
    protected final Logger log = Logger.getLogger(this.getClass());
    protected WebDriver driver;
    @FindBy(xpath = ".//div[@class='popup-wrap _active']")
    private WebElement popupVisible;

    public WebPage(WebDriver driver, WebElement element) {
        this.driver = driver;
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    public WebPage(EventFiringWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WebDriverListener.getInstance().checkUrlChange();
    }

    public WebPage(EventFiringWebDriver driver, WebPage page) {
//        this.driver = driver;
        PageFactory.initElements(driver, page);
    }

    /**
     * @param by By
     * @return WebElement founded by "By" locator
     * {@link By}
     * {@link WebElement}
     */
    public WebElement getElement(By by) {
        try {
            return driver.findElement(by);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param element WebElement
     * @return return parent element of target
     * {@link WebElement}
     */
    public WebElement getParent(WebElement element) {
        return element.findElement(By.xpath(".."));
    }

    /**
     * @param element WebElement
     * @return children of target element
     * {@link WebElement}
     */
    public List<WebElement> getChildren(WebElement element) {
        return element.findElements(By.cssSelector("*"));
    }

    /**
     * @param element  witch we want to see
     * @param waitTime time of explicit wait
     * @return boolean condition visibility of element
     * {@link WebElement}
     */
    public boolean waitForElementVisible(WebElement element, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        return wait.until(ExpectedConditions.visibilityOf(element))
                .isDisplayed();

    }

    /**
     * @param element  {@link WebElement}
     * @param waitTime int
     * @return bool
     */
    public boolean waitForElementPresent(WebElement element, int waitTime) {

        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        WebElement e = wait.pollingEvery(100, TimeUnit.MILLISECONDS).until(ExpectedConditions.visibilityOf(element));
        return e.isDisplayed();
    }

    /**
     * Wait for url change
     *
     * @param timeOutInSeconds
     * @return condition
     */
    public boolean waitForUrlChange(int timeOutInSeconds) {
        String currentUrl = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        return wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
    }

    /**
     * Return condition of disabled state of element
     *
     * @param element element
     * @return boolean disabled state
     */
    public boolean isDisabled(WebElement element) {
        return Boolean.parseBoolean(element.getAttribute("disabled"));
    }

//    public void scrollTo(WebElement element) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
//    }

    /**
     * Viewport scroll to top of page
     */
    public void scrollUp() {
        String cmd = "window.scrollTo(0, 0)";
        executeJS(cmd);
    }

    /**
     * Viewport scroll to bottom of page
     */
    public void scrollDown() {
        String cmd = "window.scrollTo(0, document.body.scrollHeight || document.documentElement.scrollHeight)";
        executeJS(cmd);
    }

    /**
     * w/o exception method for element.isDisplayed
     *
     * @param element webElement
     * @return boolean condition
     */
    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * javascript document.readyState = complete
     *
     * @return boolean condition
     */
    public boolean waitForReadyStateComplete() {
        return new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT).until((ExpectedCondition<Boolean>) wd ->
                executeJS("return document.readyState").equals("complete"));
    }

    /**
     * @param calledMethod    callable method scope {@link Callable}
     * @param waitTimeSeconds max waiting time (seconds)
     * @return boolean condition
     */
    public synchronized boolean waitFor(Callable calledMethod, int waitTimeSeconds) {
        int timer = 0;
        int steps = 0;
        try {
            while (waitTimeSeconds * 10 > timer) {
                wait(100);
                boolean result = (boolean) calledMethod.call();
                if (result) {
                    if (steps == 3) {
                        notify();
                        return true;
                    }
                    steps++;
                }
                timer++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        notify();
        return false;
    }

    /**
     * check, is element {attribute} = {value}
     *
     * @param element   WebElement
     * @param attrKey   attribute key
     * @param attrValue attribute value
     * @return boolean condition
     */
    public boolean elementHasAttribute(WebElement element, String attrKey, String attrValue) {
        return element.getAttribute(attrKey)
                .contains(attrValue);
    }

    /**
     * Thread.sleep w/o try/catch
     *
     * @param time count
     * @param unit timeUnit(ms/s/m)
     */
    public void sleep(int time, TimeUnit unit) {
        try {
            switch (unit) {
                case MILLISECONDS:
                    Thread.sleep(time);
                    break;
                case SECONDS:
                    Thread.sleep(time * 1000);
                    break;
                case MINUTES:
                    Thread.sleep(time * 1000 * 60);
                default:
                    Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Thread.sleep w/o try/catch
     *
     * @param seconds sleep in seconds
     */
    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Webdriver javascript executor
     *
     * @param jsCode javascript code
     * @return js result
     */
    public Object executeJS(String jsCode) {
        if (driver instanceof JavascriptExecutor) {
            return ((JavascriptExecutor) driver).executeScript(jsCode);
        }
        return null;
    }

    /**
     * Webdriver javascript executor to element
     *
     * @param jsCode  javascript code
     * @param element target element
     * @return js result
     */
    public Object executeJS(String jsCode, WebElement element) {
        try {
            if (driver instanceof JavascriptExecutor) {
                return ((JavascriptExecutor) driver).executeScript(jsCode, element);
            }
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param element webElement
     * @return string value of element class attribute
     */
    public String getElementClass(WebElement element) {
        return element.getAttribute("class");
    }

    /**
     * Hardware (CTRL+A , DELETE) action for input
     *
     * @param input WebElement target
     */
    public void backspaceAll(WebElement input) {
        Actions bulder = new Actions(driver);
        bulder.click(input)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
    }


    public PopUpBlock getPopUp() {
        return new PopUpBlock(driver, popupVisible);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
