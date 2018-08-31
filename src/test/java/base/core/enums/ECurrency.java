package base.core.enums;

import java.util.Objects;


public enum ECurrency {

    EUR("EUR", "€"),
    UAH("UAH", "₴"),
    USD("USD", "$"),
    GBP("GBP", "£"),
    SEK("SEK", "kr"),
    DKK("DKK", "kr"),
    NOK("NOK", "kr"),
    BRL("BRL", "BRL");

    public String code;
    public String symbol;

    ECurrency(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public static ECurrency getByText(String text) {
        for (ECurrency currency : ECurrency.values()) {
            if (Objects.equals(currency.code, text)) {
                return currency;
            }
        }
        throw new EnumConstantNotPresentException(ECurrency.class, text);

    }

    public static ECurrency getBySymbol(String symbol) {
        for (ECurrency currency : ECurrency.values()) {
            if (Objects.equals(currency.symbol, symbol)) {
                return currency;
            }
        }
        throw new EnumConstantNotPresentException(ECurrency.class, symbol);

    }
}
