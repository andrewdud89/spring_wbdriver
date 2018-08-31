package base.panel;

import base.core.iata.IATAEntity;
import base.pages.BasePanel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

public class AutocompletePanel extends BasePanel {

    public WebElement block;
    @FindBy(xpath = ".//i[@class='_input-ico']/img")
    private WebElement locationIcon;
    @FindBy(xpath = ".//input")
    private WebElement input;
    @FindBy(xpath = ".//li[contains(@class, 'item')]")
    private List<WebElement> suggestedLocationsList;
    @FindBy(xpath = ".//button[@class='autocompleter__clear-button']")
    private WebElement closeAutocompleteBtn;

    public AutocompletePanel(EventFiringWebDriver driver, WebElement element) {
        super(driver, element);
        block = element;
    }

    /**
     * @return get current location icon (plane/city)
     */
    public String getIconName() {
        String[] parse = locationIcon.getAttribute("src").split("/");
        return parse[parse.length - 1].replace(".svg", "").trim();
    }

    /**
     * @return current Selected Location
     */
    public String get() {
        return input.getAttribute("value");
    }

    /**
     * @param iata {@link IATAEntity} city or airport location
     */

    // TODO change architecture using set
//    public void set(IATAEntity iata) {
//
//        backspaceAll(input);
//        input.sendKeys(iata.getCode());
//        waitFor(() -> suggestedLocationsList.size() > 0, 5);
//
//        for (WebElement li : suggestedLocationsList) {
//            String name = li.findElement(By.tagName("span")).getText();
//            if (iata.getFullName().equals(name)) {
//                li.click();
//                return;
//            }
//        }
//        throw new WebDriverException(
//                "unable to find location with name '" + iata.getFullName() + "'"
//        );
//
//
//    }
}