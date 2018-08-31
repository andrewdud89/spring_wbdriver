package base.core.dto.request;

import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.DiscountDTO;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.LocationDTO;
import amadeus.cars.automatron.core.date.DateHelper;
import amadeus.cars.automatron.core.enums.ECurrency;
import amadeus.cars.automatron.core.enums.ELanguage;
import amadeus.cars.automatron.core.iata.IATAEntity;
import amadeus.core.convertor.Serializable;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class SearchRequest {

    private DateHelper pickupDateTime;

    private DateHelper dropoffDateTime;

    private IATAEntity pickupLocation;

    private IATAEntity dropoffLocation;

    private int driverAge;

    private List<DiscountDTO> discounts;

    private ECurrency currency;

    private ELanguage language;

    private SearchRequest() {
    }

    public static Builder builder() {
        return new SearchRequest().new Builder();
    }

    public static SearchRequest map(SearchRequestDTO dto) {

        SearchRequest entity = new SearchRequest();

        entity.pickupLocation = Serializable.deserialize(
                Serializable.serialize(dto.getPickupLocation()),
                IATAEntity.class);

        entity.dropoffLocation = Serializable.deserialize(
                Serializable.serialize(dto.getDropoffLocation()),
                IATAEntity.class
        );

        entity.pickupDateTime = new DateHelper(Integer.parseInt(dto.getPickDateTime()), DateHelper.ISO_PATTERN);
        entity.dropoffDateTime = new DateHelper(Integer.parseInt(dto.getDropDateTime()), DateHelper.ISO_PATTERN);

        entity.currency = ECurrency.getByText(dto.getCurrency());
        entity.language = ELanguage.getByCode(dto.getLanguage());
        entity.driverAge = dto.getDriverAge();
        entity.discounts = dto.getDiscounts();
        return entity;
    }

    public DateHelper getPickupDateTime() {
        return pickupDateTime;
    }

    public DateHelper getDropoffDateTime() {
        return dropoffDateTime;
    }

    public IATAEntity getPickupLocation() {
        return pickupLocation;
    }

    public IATAEntity getDropoffLocation() {
        return dropoffLocation;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public List<DiscountDTO> getDiscounts() {
        return discounts;
    }

    public ECurrency getCurrency() {
        return currency;
    }

    public ELanguage getLanguage() {
        return language;
    }

    public class Builder {


        private Builder() {

        }

        @Step("setPickupDateTime {0}")
        public Builder setPickupDateTime(DateHelper dateHelper) {
            SearchRequest.this.pickupDateTime = dateHelper;
            return this;
        }

        @Step("setReturnDateTime {0}")
        public Builder setDropoffDateTime(DateHelper dropoffDateTime) {
            SearchRequest.this.dropoffDateTime = dropoffDateTime;
            return this;

        }

        @Step("setPickupLocation {0}")
        public Builder setPickupLocation(IATAEntity pickupLocation) {
            SearchRequest.this.pickupLocation = pickupLocation;
            return this;

        }

        @Step("setDropoffLocation {0}")
        public Builder setDropoffLocation(IATAEntity dropoffLocation) {
            SearchRequest.this.dropoffLocation = dropoffLocation;
            return this;

        }

        @Step("setDriverAge {0}")
        public Builder setDriverAge(int driverAge) {
            SearchRequest.this.driverAge = driverAge;
            return this;

        }

        @Step("setDiscounts {0}")
        public Builder setDiscounts(List<DiscountDTO> discount) {
            SearchRequest.this.discounts = discount;
            return this;

        }

        @Step("addDiscount{0}")
        public Builder addDiscount(DiscountDTO discountEntity) {
            SearchRequest.this.discounts.add(discountEntity);
            return this;
        }

        @Step("setCurrency {0}")
        public Builder setCurrency(ECurrency currency) {
            SearchRequest.this.currency = currency;
            return this;

        }

        @Step("setLanguage {0}")
        public Builder setLanguage(ELanguage language) {
            SearchRequest.this.language = language;
            return this;
        }

        public SearchRequestDTO build() {

            checkNulls("pickupLocation", pickupLocation);
            checkNulls("pickupDateTime", pickupDateTime);
            checkNulls("dropoffDateTime", dropoffDateTime);
            checkNulls("driverAge", driverAge);
            checkNulls("language", language);
            checkNulls("currency", currency);

            LocationDTO pLoc = Serializable.deserialize(
                    Serializable.serialize(pickupLocation),
                    LocationDTO.class
            );
            LocationDTO dLoc;

            if (dropoffLocation != null) {
                dLoc = Serializable.deserialize(
                        Serializable.serialize(dropoffLocation, true),
                        LocationDTO.class
                );
            } else {
                dLoc = Serializable.deserialize(
                        Serializable.serialize(pickupLocation, true),
                        LocationDTO.class
                );
            }
            List<DiscountDTO> discounts = new ArrayList<>();

            if (SearchRequest.this.discounts != null) {
                for (DiscountDTO discountEntity : SearchRequest.this.discounts) {
                    discounts.add(
                            Serializable.deserialize(
                                    Serializable.serialize(discountEntity),
                                    DiscountDTO.class
                            )
                    );
                }
            }

            String pTime = pickupDateTime.toString(DateHelper.ISO_PATTERN);
            String dTime = dropoffDateTime.toString(DateHelper.ISO_PATTERN);

            return new SearchRequestDTO(
                    pLoc, pTime, dLoc, dTime, currency.code, language.code, driverAge, discounts
            );
        }

        private void checkNulls(String name, Object object) {
            if (object == null) {
                throw new NullPointerException(String.format("object '%s' is null", name));
            }
        }
    }
}
