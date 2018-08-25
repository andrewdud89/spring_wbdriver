package config;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@Configuration
@ComponentScan(basePackages = {"core.*", "tests.*"})
public class TestConfig {

    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Andrii Simple\\Downloads\\chromedriver_win32\\chromedriver.exe");
    }

    @Bean(destroyMethod = "quit")
    @Scope("singleton")
    public WebDriver webDriver() {
        return new ChromeDriver();
    }

}
