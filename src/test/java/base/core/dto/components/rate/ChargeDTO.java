package base.core.dto.components.rate;

import java.util.ArrayList;
import java.util.List;

public class ChargeDTO {

    private double amount;

    private String type;

    private String code;

    private boolean converted;

    private String currency;

    private String description;

    private String interactionType;

    private String periodType;

    private String periodTypeName;

    private int quantity;

    private String paymentTimeType;
    private List<ExtraInfoDTO> extraInfo;

    public List<ExtraInfoDTO> getExtraInfo() {
        if (extraInfo == null) {
            extraInfo = new ArrayList<>();
        }
        return extraInfo;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public boolean isConverted() {
        return converted;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public String getPeriodType() {
        return periodType;
    }

    public String getPeriodTypeName() {
        return periodTypeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPaymentTimeType() {
        return paymentTimeType;
    }
}