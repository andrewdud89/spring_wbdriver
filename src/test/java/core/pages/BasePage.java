package core.pages;

import config.TestConfig;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Component
public class BasePage {

    @Autowired
    protected WebDriver webDriver;

    public BasePage() {
        int a=2;
    }

    public void openPage() {
        webDriver.get("https://www.google.com.ua");
    }

}
