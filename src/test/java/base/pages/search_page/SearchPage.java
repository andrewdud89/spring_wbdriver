package base.pages.search_page;

import base.pages.CarsPage;
import base.pages.DiscountsBlock;
import base.pages.SearchFormBlock;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends CarsPage {

    @FindBy(xpath = "//app-search-form")
    private WebElement appSearchForm;

    @FindBy(xpath = "//app-search-discount")
    private WebElement appSearchDiscounts;

    @FindBy(xpath = "//div[@class='coupon-activator']/button")
    private WebElement discountsActivator;

    @FindBy(xpath = "//div[@class='coupon-deactivator']/button")
    private WebElement discountsDectivator;

    public SearchPage() {

    }

    public SearchFormBlock getSearchForm() {
        return new SearchFormBlock(getDriver(), appSearchForm);
    }

    public DiscountsBlock getDiscountsBlock() {
        showDiscounts();
        return new DiscountsBlock(getDriver(), appSearchDiscounts);
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
