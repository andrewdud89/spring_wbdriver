package base.core.dto.components;

import javax.annotation.Generated;

@Generated("io.t28.json2java.core.JavaConverter")
@SuppressWarnings("all")
public class PaymentDTO {
    private final String cardHolderName;

    private final String cardNumber;

    private final String expirationMonth;

    private final String expirationYear;

    private final String securityCode;

    public PaymentDTO(String cardHolderName, String cardNumber, String expirationMonth,
                      String expirationYear, String securityCode) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.securityCode = securityCode;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public String getSecurityCode() {
        return securityCode;
    }
}
