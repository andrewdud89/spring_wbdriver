package base.panel;


import base.core.translation.Translations;
import base.core.utils.AllureUtils;
import base.date.DateHelper;
import base.pages.BasePanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.allure.annotations.Step;

public class SearchFormPanel extends BasePanel {

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

    public SearchFormPanel(EventFiringWebDriver driver, WebElement element) {
        super(driver, element);
        this.block = element;
    }

    public AutocompleteBlock pickUpLocation() {
        return new AutocompleteBlock(getDriver(), pickupAutocompleter);
    }

    public AutocompleteBlock dropOffLocation() {
        clickDropoffLocationOther();
        return new AutocompleteBlock(getDriver(), dropoffAutocompleter);
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
        AllureUtils.clickNotify("Search Button click : " + getDriver().getCurrentUrl());
    }

    private void waitForUrlChange(int i) {

    }

    public int getDriverAge() {
        return Integer.parseInt(new Select(driverAgeSelect).getFirstSelectedOption().getText().trim());
    }

    @Step("Set Driver age {0}")
    public void setDriverAge(int age) {
        new Select(driverAgeSelect).selectByValue(String.valueOf(age));
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
