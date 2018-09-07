package base.pages;

import base.core.dto.components.DiscountDTO;
import base.core.enums.ECarProviders;
import base.core.enums.EDiscountTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class DiscountsBlock extends BasePanel {

    @FindBy(xpath = ".//select[contains(@class,'provider-select')]")
    private WebElement providerSelect;

    @FindBy(xpath = ".//select[contains(@class,'discount-select')]")
    private WebElement discountTypeSelect;

    @FindBy(xpath = ".//input[@name='code']")
    private WebElement discountCodeInput;

    @FindBy(xpath = ".//div[@class='coupon-wrap__add']/button")
    private WebElement discountAdd;

    @FindBy(xpath = ".//div[@class='coupon-wrap']")
    private List<WebElement> discounts;

    public DiscountsBlock(WebDriver driver, WebElement element) {
        super(driver, element);
    }


    @Step("Discount add : {0}")
    public void addDiscount(DiscountDTO discount) {


        setProvider(discount.getProviderCode())
                .setType(discount.getType())
                .setCode(discount.getCode())
                .Add();
    }

    public void addDiscount(ECarProviders provider, EDiscountTypes type, String code) {
        addDiscount(new DiscountDTO(provider.code, type.discountType, code));
    }


    public DiscountsBlock setProvider(String providerCode) {
        if (providerCode == null) {
            return this;
        }
        Select select = new Select(providerSelect);
        select.selectByVisibleText(providerCode);
        return this;
    }

    public DiscountsBlock setType(String type) {
        if (type == null) {
            return this;
        }
        Select select2 = new Select(discountTypeSelect);
        select2.selectByValue(type);
        return this;
    }

    public DiscountsBlock setCode(String code) {
        if (code == null) {
            return this;
        }
        discountCodeInput.sendKeys(code);
        return this;
    }

    public void Add() {
        discountAdd.click();
    }

    public List<ECarProviders> getDiscountProviders() {
        Select select = new Select(providerSelect);
        List<ECarProviders> providers = new ArrayList<>();

        for (int i = 1; i < select.getOptions().size(); i++) {
            providers.add(ECarProviders.getProviderByTitle(select.getOptions().get(i).getText().trim()));
        }
        return providers;
    }

    public List<EDiscountTypes> getDiscountTypes() {
        Select select = new Select(discountTypeSelect);
        List<EDiscountTypes> types = new ArrayList<>();
        for (int i = 1; i < select.getOptions().size(); i++) {
            types.add(EDiscountTypes.valueOf(select.getOptions().get(i).getText().trim()));
        }
        return types;
    }

    public List<DiscountDTO> getAddedDiscounts() {
        List<DiscountDTO> entities = new ArrayList<>();
        for (int i = 1; i < discounts.size(); i++) { // zero item - is discount add row. i=1 - ignore it:)
            WebElement row = discounts.get(i);
            String provider = row.findElement(By.xpath(".//div[@class='coupon-wrap__provider']")).getText().trim();
            String type = row.findElement(By.xpath(".//div[@class='coupon-wrap__type']")).getText().trim();
            String code = row.findElement(By.xpath(".//div[@class='coupon-wrap__number']")).getText();

            entities.add(new DiscountDTO(ECarProviders.getProviderByTitle(provider).code, type, code));
        }
        return entities;
    }

    @Step("Remove Discount {0}")
    public void removeDiscount(DiscountDTO discount) {
        List<DiscountDTO> entities = getAddedDiscounts();
        for (int i = 0; i < entities.size(); i++) {
            DiscountDTO added = entities.get(i);
            if (added.getProviderCode().equals(discount.getCode()) && added.getCode().equals(discount.getCode())) {
                discounts.get(i + 1).findElement(By.xpath(".//div[contains(@class,'coupon-wrap__add--remove')]")).click();
            }
        }
    }


}
