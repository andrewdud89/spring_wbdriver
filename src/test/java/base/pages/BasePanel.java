package base.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

abstract public class BasePanel {

    private final WebDriver driver;

    public BasePanel(WebDriver driver, WebElement element) {
        this.driver = driver;
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
