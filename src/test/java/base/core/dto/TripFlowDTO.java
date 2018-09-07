package base.core.dto;


import base.core.dto.components.DiscountDTO;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TripFlowDTO {
    private String backLinkUrl;

    private String travelerFirstName;

    private String travelerLastName;

    private String travelerEmail;

    private String travelerPhone;

    private String pickUpLocationCode;

    private String pnrRecordLocator;

    private String dropOffLocationCode;

    private String pickUpDateTime;

    private String languageCode;

    private String dropOffDateTime;

    @SerializedName("FF")
    private String ff;

    private List<String> providers;

    private List<DiscountDTO> discounts;

    public String getBackLinkUrl() {
        return backLinkUrl;
    }

    public String getTravelerFirstName() {
        return travelerFirstName;
    }

    public String getTravelerLastName() {
        return travelerLastName;
    }

    public String getTravelerEmail() {
        return travelerEmail;
    }

    public String getTravelerPhone() {
        return travelerPhone;
    }

    public String getPickUpLocationCode() {
        return pickUpLocationCode;
    }

    public String getDropOffLocationCode() {
        return dropOffLocationCode;
    }

    public String getPickUpDateTime() {
        return pickUpDateTime;
    }

    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public List<String> getProviders() {
        return providers;
    }

    public String getPnrRecordLocator() {
        return pnrRecordLocator;
    }

    public String getFf() {
        return ff;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public List<DiscountDTO> getDiscounts() {
        return discounts;
    }
}
