package base.core.dto.components.offer;

import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.DiscountDTO;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.LocationDTO;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.ProviderDTO;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.rate.ChargeDTO;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.rate.PriceBreakDownDTO;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.rate.RateDetailsDTO;
import amadeus.core.convertor.Serializable;

import java.util.ArrayList;
import java.util.List;

public class OfferDTO extends Serializable {
    private List<ChargeDTO> additionalOptions;
    private List<CustomerRatingsDTO> customerRatings;
    private List<DiscountDTO> discounts;
    private LocationDTO dropoffLocation;
    private String dropoffTime;
    private ExtraRateTypeInfoDTO extraRateTypeInfo;
    private String id;
    private MileageIncludedDTO mileageIncluded;
    private String paymentInfo;
    private LocationDTO pickupLocation;
    private String pickupTime;
    private PriceBreakDownDTO priceBreakdown;
    private ProviderDTO provider;
    private String providerRateIdentifier;
    private String providerRateType;
    private RateDetailsDTO rateDetails;
    private List<ChargeDTO> rates;
    private String referenceNumber;
    private int rentalDurationInDays;
    private String validUntil;
    private VehicleDTO vehicle;
    private String includedSurcharges;
    private String notIncludedSurcharges;

    public String getIncludedSurcharges() {
        return includedSurcharges;
    }

    public String getNotIncludedSurcharges() {
        return notIncludedSurcharges;
    }

    public String getProviderRateType() {
        return providerRateType;
    }

    public RateDetailsDTO getRateDetails() {
        return rateDetails;
    }

    public String getId() {
        return id;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public ProviderDTO getProvider() {
        return provider;
    }

    public String getProviderRateIdentifier() {
        return providerRateIdentifier;
    }

    public List<ChargeDTO> getRates() {
        if (rates == null)
            rates = new ArrayList<>();
        return rates;
    }

    public LocationDTO getPickupLocation() {
        return pickupLocation;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public LocationDTO getDropoffLocation() {
        return dropoffLocation;
    }

    public String getDropoffTime() {
        return dropoffTime;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public MileageIncludedDTO getMileageIncluded() {
        return mileageIncluded;
    }

    public List<DiscountDTO> getDiscounts() {
        if (discounts == null) {
            discounts = new ArrayList<>();
        }
        return discounts;
    }

    public int getRentalDurationInDays() {
        return rentalDurationInDays;
    }

    public ExtraRateTypeInfoDTO getExtraRateTypeInfo() {
        return extraRateTypeInfo;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public List<CustomerRatingsDTO> getCustomerRatings() {
        if (customerRatings == null)
            customerRatings = new ArrayList<>();
        return customerRatings;
    }

    public List<ChargeDTO> getAdditionalOptions() {
        if (additionalOptions == null) {
            additionalOptions = new ArrayList<>();
        }
        return additionalOptions;
    }

    public PriceBreakDownDTO getPriceBreakdown() {
        return priceBreakdown;
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getId(), getProvider().getCode());
    }
}