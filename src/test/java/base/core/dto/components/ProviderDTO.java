package base.core.dto.components;

import base.core.dto.components.offer.BrokerDTO;

import java.util.List;

public class ProviderDTO {

    private String code;

    private String name;

    private String logoUrl;

    private List<DiscountDTO> discounts;

    private int bonusMiles;

    private BrokerDTO broker;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public List<DiscountDTO> getDiscounts() {
        return discounts;
    }

    public int getBonusMiles() {
        return bonusMiles;
    }

    public BrokerDTO getBroker() {
        return broker;
    }

}
