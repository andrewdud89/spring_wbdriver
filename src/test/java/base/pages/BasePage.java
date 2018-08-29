package base.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;


public class BasePage {

    @Autowired
    protected WebDriver webDriver;

    @PostConstruct
    public void doInit(){
        PageFactory.initElements(webDriver, this);

    }
    public void openPage() {
        webDriver.get("https://www.google.com.ua");
    }

}
