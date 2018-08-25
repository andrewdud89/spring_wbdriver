package core.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class SearchPage extends BasePage {

    @FindBy(xpath = "ddd")
    private WebElement input;

    public void fillInputField() {
        input.sendKeys("test");
    }

    public SearchPage() {
        PageFactory.initElements(webDriver, this);
    }
}
