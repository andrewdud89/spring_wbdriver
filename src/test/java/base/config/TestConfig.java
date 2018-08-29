//package base.config;
//
//
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ComponentScan(basePackages = {"core.*", "tests.*"})
//public class TestConfig {
//
//    static {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Andrii Simple\\Downloads\\chromedriver_win32\\chromedriver.exe");
//    }
//
//    @Bean(destroyMethod = "quit")
//
//    public WebDriver webDriver() {
//
//
//        return new ChromeDriver();
//    }
//
//}
