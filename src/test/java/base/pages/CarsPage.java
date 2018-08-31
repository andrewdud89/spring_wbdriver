package base.pages;

import base.core.convertor.Serializable;
import base.core.dto.ClientConfigDTO;
import base.core.dto.FrontendConfigDTO;
import base.core.dto.TripFlowDTO;
import base.core.enums.ECurrency;
import base.core.enums.ELanguage;
import base.core.listeners.WebDriverListener;
import base.core.translation.Translations;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class CarsPage extends BasePage {

    @FindBy(xpath = "//app-currency-switcher//select")
    private WebElement navCurrencyElement;
    @FindBy(xpath = "//app-locale-switcher//select")
    private WebElement navLanguageElement;
    @FindBy(xpath = "//app-root")
    private WebElement appRoot;
    @FindBy(xpath = "//div[@role='progressbar']")
    private WebElement progressBar;
    @FindBy(xpath = "//nav/a")
    private WebElement myBookingsLink;
    @FindBy(xpath = "//a[@class='header__logo']")
    private WebElement headerLogo;

//    public CarsPage(EventFiringWebDriver driver) {
//        super(driver);
//        Translations.getInstance().setLanguage(getLanguage());
//    }

    public void logoClick() {
        headerLogo.click();
    }

    /**
     * Currency select method
     *
     * @param currencyValue Enum desired currency
     */

    public void selectCurrency(ECurrency currencyValue) {
        if (Objects.equals(getCurrency(), currencyValue)) {
            return;
        }
        Select currency = new Select(navCurrencyElement);
        currency.selectByValue(currencyValue.code);
        sleep(100, TimeUnit.MILLISECONDS);
    }

    /**
     * Language select method
     *
     * @param languageValue Enum desired language
     */

    public void selectLanguage(ELanguage languageValue) {
        if (Objects.equals(getLanguage(), languageValue)) {
            return;
        }
        Select language = new Select(navLanguageElement);
        language.selectByValue(languageValue.code);
        Translations.getInstance().setLanguage(languageValue);
        sleep(100, TimeUnit.MILLISECONDS);
    }

    /**
     * @return list of languages on page {@link List} {@link ELanguage}
     */
    public List<ELanguage> getLanguages() {
        Select language = new Select(navLanguageElement);
        List<ELanguage> languages = new ArrayList<>();
        language.getOptions().forEach(element -> {
            String langVal = element.getAttribute("value");
            languages.add(ELanguage.getByCode(langVal));
        });
        return languages;
    }

    /**
     * @return list of currencies on page {@link List} {@link ECurrency}
     */
    public List<ECurrency> getCurrencies() {
        Select currency = new Select(navCurrencyElement);
        List<ECurrency> currencies = new ArrayList<>();
        currency.getOptions().forEach(element -> {
            String langVal = element.getAttribute("value");
            currencies.add(ECurrency.getByText(langVal));
        });
        return currencies;
    }

    /**
     * @param element {@link WebElement}
     * @return bool
     */
    public boolean isEnabled(WebElement element) {
        return !Boolean.parseBoolean(element.getAttribute("disabled"));
    }

    /**
     * Callable method
     *
     * @return boolean is load complete in maximum wait time (DEFAULT_EXPLICIT_WAIT)
     */
    public boolean loadComplete() {
        return loadComplete(DEFAULT_EXPLICIT_WAIT);
    }

    /**
     * @param waitTime maximum waiting time in seconds
     * @return boolean is load complete after waitTime.
     */
    public boolean loadComplete(int waitTime) {
        boolean result = waitFor(() -> !isElementPresent(progressBar), waitTime);
        WebDriverListener.getInstance().checkUrlChange();
        return result;
    }

    /**
     * @return current selected Currency on page as enum {@link ECurrency}
     */
    public ECurrency getCurrency() {
        return ECurrency.getByText(new Select(navCurrencyElement).getFirstSelectedOption().getText().trim());
    }

    /**
     * @return current selected language as ENUM {@link ELanguage}
     */
    public ELanguage getLanguage() {
        String opt = new Select(navLanguageElement).getFirstSelectedOption().getAttribute("value").trim();
        return ELanguage.getByCode(opt);
    }

    public ClientConfigDTO getClientConfig() {
        return Serializable.deserialize(executeJS("return JSON.stringify(clientConfig)"), ClientConfigDTO.class);
    }

    public TripFlowDTO getTripFlowParams() {
        TripFlowDTO dto = Serializable.deserialize(executeJS("return JSON.stringify(tripFlow)"), TripFlowDTO.class);
        return dto;
    }

    public void myBookingsClick() {
        scrollTo(myBookingsLink);
        myBookingsLink.click();
    }

    public boolean isCurrencyPresent() {
        return isElementPresent(navCurrencyElement);
    }

    public FrontendConfigDTO getFrontendConfig() {
        return Serializable.deserialize(executeJS("return JSON.stringify(frontendConfig)"), FrontendConfigDTO.class);
    }

}
