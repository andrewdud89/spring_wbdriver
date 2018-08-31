package base.pages.search_page;

import amadeus.cars.automatron.carsbookingengine.whiteLabel.pageObject.blocks.DiscountsBlock;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.pageObject.blocks.SearchFormBlock;
import base.pages.CarsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class SearchPage extends CarsPage {

    @FindBy(xpath = "//app-search-form")
    private WebElement appSearchForm;

    @FindBy(xpath = "//app-search-discount")
    private WebElement appSearchDiscounts;

    @FindBy(xpath = "//div[@class='coupon-activator']/button")
    private WebElement discountsActivator;

    @FindBy(xpath = "//div[@class='coupon-deactivator']/button")
    private WebElement discountsDectivator;

    public SearchPage(EventFiringWebDriver driver) {
        super(driver);
    }

    public SearchFormBlock getSearchForm() {
        return new SearchFormBlock(driver, appSearchForm);
    }

    public DiscountsBlock getDiscountsBlock() {
        showDiscounts();
        return new DiscountsBlock(driver, appSearchDiscounts);
    }

    public void showDiscounts() {
        if (isElementPresent(discountsActivator)) {
            discountsActivator.click();
        }
    }

    public void hideDiscouns() {
        if (isElementPresent(discountsDectivator)) {
            discountsDectivator.click();
        }
    }
}
