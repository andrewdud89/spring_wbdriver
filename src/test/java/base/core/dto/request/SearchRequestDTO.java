package base.core.dto.request;

import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.DiscountDTO;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.LocationDTO;
import amadeus.core.convertor.Serializable;

import java.util.List;


public class SearchRequestDTO extends Serializable {
    private final LocationDTO pickupLocation;

    private final String pickDateTime;

    private final LocationDTO dropoffLocation;

    private final String dropDateTime;

    private final String currency;

    private final String language;

    private final int driverAge;

    private final List<DiscountDTO> discounts;

    public SearchRequestDTO(LocationDTO pickupLocation, String pickDateTime,
                            LocationDTO dropoffLocation, String dropDateTime, String currency, String language,
                            int driverAge, List<DiscountDTO> discounts) {
        this.pickupLocation = pickupLocation;
        this.pickDateTime = pickDateTime;
        this.dropoffLocation = dropoffLocation;
        this.dropDateTime = dropDateTime;
        this.currency = currency;
        this.language = language;
        this.driverAge = driverAge;
        this.discounts = discounts;
    }

    public LocationDTO getPickupLocation() {
        return pickupLocation;
    }

    public String getPickDateTime() {
        return pickDateTime;
    }

    public LocationDTO getDropoffLocation() {
        return dropoffLocation;
    }

    public String getDropDateTime() {
        return dropDateTime;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLanguage() {
        return language;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public List<DiscountDTO> getDiscounts() {
        return discounts;
    }


}
