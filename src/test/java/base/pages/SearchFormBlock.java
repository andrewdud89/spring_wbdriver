package base.pages;


import base.core.CarsEnvironment;
import base.core.api.WhiteLabelApi;
import base.core.convertor.Serializable;
import base.core.dto.components.LocationDTO;
import base.core.iata.Airport;
import base.core.iata.City;
import base.core.iata.IATAEntity;
import base.core.translation.Translations;
import base.core.utils.AllureUtils;
import base.date.DateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class SearchFormBlock extends WebPage {

    private WebElement block;

    @FindBy(xpath = "(.//app-autocompleter)[1]")
    private WebElement pickupAutocompleter;

    @FindBy(xpath = "(.//app-autocompleter)[2]")
    private WebElement dropoffAutocompleter;

    @FindBy(xpath = "(.//app-datepicker)[1]")
    private WebElement pickupDatePicker;

    @FindBy(xpath = "(.//app-time-select)[1]")
    private WebElement pickupTimePicker;

    @FindBy(xpath = "(.//app-datepicker)[2]")
    private WebElement dropoffDatePicker;

    @FindBy(xpath = "(.//app-time-select)[2]")
    private WebElement dropoffTimePicker;

    @FindBy(xpath = "(.//label[@class='location-info__radio-btn'])[2]")
    private WebElement dropoffLocationOther;

    @FindBy(xpath = "(.//label[@class='location-info__radio-btn'])[1]")
    private WebElement dropofflocationSame;

    @FindBy(xpath = ".//button[@class='search-form__btn']")
    private WebElement searchBtn;

    @FindBy(xpath = ".//select[contains(@class,'driver-age__select')]")
    private WebElement driverAgeSelect;

    public SearchFormBlock(WebDriver driver, WebElement element) {
        super(driver, element);
        this.block = element;
    }

    public AutocompleteBlock pickUpLocation() {
        return new AutocompleteBlock(driver, pickupAutocompleter);
    }

    public AutocompleteBlock dropOffLocation() {
        clickDropoffLocationOther();
        return new AutocompleteBlock(driver, dropoffAutocompleter);
    }

    public DatePickerBlock pickUpDateTime() {
        return new DatePickerBlock(pickupDatePicker, pickupTimePicker);
    }

    public DatePickerBlock dropOffDateTime() {
        return new DatePickerBlock(dropoffDatePicker, dropoffTimePicker);
    }


    public void clickDropoffLocationOther() {
        dropoffLocationOther.click();
        AllureUtils.clickNotify("click Dropoff Location Other");
    }

    public void clickDropoffLocationSame() {
        dropofflocationSame.click();
        AllureUtils.clickNotify("click Dropoff Location Same");
    }

    public boolean searchEnabled() {
        return searchBtn.isEnabled();
    }

    public void search() {
        searchBtn.click();
        waitForUrlChange(5);
        AllureUtils.clickNotify("Search Button click : " + driver.getCurrentUrl());
    }

    public int getDriverAge() {
        return Integer.parseInt(new Select(driverAgeSelect).getFirstSelectedOption().getText().trim());
    }

    @Step("Set Driver age {0}")
    public void setDriverAge(int age) {
        new Select(driverAgeSelect).selectByValue(String.valueOf(age));
    }

    public static class AutocompleteBlock extends WebPage {

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
        @Step("Set Location: {0}")
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

        public void closeDropDown() {
            closeAutocompleteBtn.click();
        }

        public void clear() {
            if (isElementPresent(closeAutocompleteBtn))
                closeAutocompleteBtn.click();
        }
    }

    public static class DatePickerBlock {

        private WebElement dateInput;
        private Select timeSelect;

        public DatePickerBlock(WebElement date, WebElement time) {
            dateInput = date.findElement(By.xpath(".//input"));
            timeSelect = new Select(time.findElement(By.xpath(".//select")));
        }

        public void setDate(DateHelper date) {

            String pattern = Translations.getInstance().getPattern().searchForm();
            dateInput.clear();
            dateInput.sendKeys(date.toString(pattern));
        }

        public DateHelper getDateTime() {
            return new DateHelper(getDate(), Translations.getInstance().getPattern().searchForm()).addTime(getTime().trim());
        }

        public void setDateTime(DateHelper dateTime) {
            setDate(dateTime);
            setTime(dateTime.getDateTime().getHourOfDay());
        }

        public String getTime() {
            return timeSelect.getFirstSelectedOption().getText().trim();
        }

        public void setTime(int hours) {
            timeSelect.selectByValue(String.valueOf(hours));

        }

        public String getDate() {
            return dateInput.getAttribute("value");
        }

        public void setDate(String date) {
            dateInput.sendKeys(date);
        }

        public int getMinutes() {
            return Integer.parseInt(getTime().trim().replace(":", "")) / 100 * 60;
        }
    }
}
