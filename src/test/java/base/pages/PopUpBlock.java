package base.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PopUpBlock extends BasePanel {

    @FindBy(xpath = "//app-popup/div[@class='popup-wrap _active']")
    private WebElement popupBlock;

    @FindBy(xpath = "(.//button[contains(@class, 'button--popup')])[1]")
    private WebElement yesBtn;

    @FindBy(xpath = "(.//button)[2]")
    private WebElement noBtn;

    @FindBy(xpath = ".//div[@class='popup__body']")
    private WebElement body;

    @FindBy(xpath = ".//div[@class='popup__head']")
    private WebElement head;

    public PopUpBlock(WebDriver driver, WebElement root) {
        super(driver, root);

    }

    public String getContent() {
        return body.getText().trim();
    }

    public boolean clickYes() {
//        waitForElementPresent(yesBtn, 5);
        yesBtn.click();
//        sleep(5);
        return true;
    }

    public boolean clickClose() {
        return clickYes();
    }

    public void clickNo() {
        noBtn.click();
    }

    public String getHeaderText() {
        return head.getText().trim();
    }
}
