package base.core.dto.components.rate;

import base.core.convertor.Serializable;

import java.util.List;

public class RateDetailsDTO extends Serializable {
    private List<ChargeDTO> additionalServices;

    private int rateOrder;

    private List<ChargeDTO> chargeDetails;

    private String freeMileage;

    private PriceBreakDownDTO priceBreakdown;

    private List<ChargeDTO> rateIncludes;

    private String termsAndConditionsUrl;

    private RateRulesDTO rateRules;

    public RateRulesDTO getRateRules() {
        return rateRules;
    }

    public List<ChargeDTO> getAdditionalServices() {
        return additionalServices;
    }

    public List<ChargeDTO> getChargeDetails() {
        return chargeDetails;
    }

    public String getFreeMileage() {
        return freeMileage;
    }

    public PriceBreakDownDTO getPriceBreakdown() {
        return priceBreakdown;
    }

    /**
     * use getPriceBreakdown().getRateIncludes().
     */
    @Deprecated
    public List<ChargeDTO> getRateIncludes() {
        return rateIncludes;
    }

    public int getRateOrder() {
        return rateOrder;
    }

    public String getTermsAndConditionsUrl() {
        return termsAndConditionsUrl;
    }

}
