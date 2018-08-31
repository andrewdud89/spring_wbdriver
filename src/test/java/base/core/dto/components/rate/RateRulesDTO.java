package base.core.dto.components.rate;

import java.util.List;

public class RateRulesDTO {
    private List<String> cardTypes;

    private List<String> paymentTypes;

    public List<String> getCardTypes() {
        return cardTypes;
    }

    public List<String> getPaymentTypes() {
        return paymentTypes;
    }
}
