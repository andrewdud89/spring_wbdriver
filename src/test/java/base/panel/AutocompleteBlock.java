package base.panel;

import base.core.CarsEnvironment;
import base.core.api.WhiteLabelApi;
import base.core.convertor.Serializable;
import base.core.dto.components.LocationDTO;
import base.core.iata.Airport;
import base.core.iata.City;
import base.core.iata.IATAEntity;
import base.pages.BasePanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class AutocompleteBlock extends BasePanel {

    public WebElement block;
    @FindBy(xpath = ".//i[@class='_input-ico']/img")
    private WebElement locationIcon;
    @FindBy(xpath = ".//input")
    private WebElement input;
    @FindBy(xpath = ".//li[contains(@class, 'item')]")
    private List<WebElement> suggestedLocationsList;
    @FindBy(xpath = ".//button[@class='autocompleter__clear-button']")
    private WebElement closeAutocompleteBtn;

    public AutocompleteBlock(WebDriver driver, WebElement element) {
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

    public void set(IATAEntity iata) {

        backspaceAll(input);
        input.sendKeys(iata.getCode());
        waitFor(() -> suggestedLocationsList.size() > 0, 5);

        for (WebElement li : suggestedLocationsList) {
            String name = li.findElement(By.tagName("span")).getText();
            if (iata.getFullName().equals(name)) {
                li.click();
                return;
            }
        }
        throw new WebDriverException(
                "unable to find location with name '" + iata.getFullName() + "'"
        );

    }

    public List<IATAEntity> getSuggested(String code) {
        List<IATAEntity> suggestions = new ArrayList<>();

        backspaceAll(input);
        input.sendKeys(code);

        waitFor(() -> suggestedLocationsList.size() > 0, 5);
        WhiteLabelApi api = new WhiteLabelApi(CarsEnvironment.getInstance().getWhiteLabelUrl());

        List<LocationDTO> data = api.getIATA(code);

        assert data.size() == suggestedLocationsList.size();

        for (WebElement li : suggestedLocationsList) {
            String name = li.findElement(By.tagName("span")).getText();
            data.stream()
                    .filter(locationDTO -> locationDTO.getFullName().equals(name))
                    .findFirst()
                    .ifPresent(locationDTO -> {
                        if (locationDTO.isAirport()) {
                            suggestions.add(Serializable.deserialize(locationDTO.serialize(), Airport.class));
                        } else {
                            suggestions.add(Serializable.deserialize(locationDTO.serialize(), City.class));
                        }
                    });
        }
        return suggestions;
    }

    private void backspaceAll(WebElement input) {

    }

    public void closeDropDown() {
        closeAutocompleteBtn.click();
    }

    public void clear() {
//        if (isElementPresent(closeAutocompleteBtn))
        closeAutocompleteBtn.click();
    }

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
}