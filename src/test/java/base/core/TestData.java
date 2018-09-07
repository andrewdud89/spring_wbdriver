package base.core;


import base.core.annotations.RequiredField;
import base.core.dto.components.DiscountDTO;
import base.core.enums.ECarProviders;
import base.core.enums.ECurrency;
import base.core.enums.ELanguage;
import base.core.iata.IATA;
import base.core.iata.IATAEntity;
import base.date.DateHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * CarsTest DTO.
 * Deserialize from {dir}/testData/{testData}.json. or jenkins env='project.jenkins.data'.
 * Required for each {@link WebDriverScenario} class
 */
public class TestData {

    @RequiredField
    private String env;

    @RequiredField
    private String client;

    @RequiredField
    private Location pickUp;

    private Location dropOff;

    private List<Discounts> discounts;

    private List<String> providers;

    private String currency;

    private String language;

    @RequiredField
    private String officeId;

    private DriverInfo driverInfo;

    public Location getPickUp() {
        return pickUp;
    }

    public Location getDropOff() {
        return dropOff;
    }

    public List<DiscountDTO> getDiscounts() {
        List<DiscountDTO> discountsList = new ArrayList<>();
        for (Discounts discount : discounts) {
            discountsList.add(new DiscountDTO(discount.provider, discount.type, discount.code));
        }
        return discountsList;
    }

    public CarsEnvironment getEnv() {
        return CarsEnvironment.getInstance(client, env);
    }

    public List<ECarProviders> getProviders() {
        List<ECarProviders> providers = new ArrayList<>();
        if (this.providers != null)
            for (String bookProvider : this.providers) {
                providers.add(ECarProviders.getProviderByCode(bookProvider));
            }

        return providers;
    }

    public String getClient() {
        return client;
    }

    public IATAEntity getPickUpLocation() {
        if (pickUp.isAirport()) {
            return IATA.airport(pickUp.location);
        } else {
            return IATA.city(pickUp.location);
        }
    }

    public IATAEntity getDropOffLocation() {
        if (dropOff.isAirport()) {
            return IATA.airport(dropOff.location);
        } else {
            return IATA.city(dropOff.location);
        }
    }

    public ECurrency getCurrency() {
        if (currency != null)
            return ECurrency.valueOf(currency.toUpperCase());
        return null;
    }

    public ELanguage getLanguage() {
        if (language != null)
            return ELanguage.getByCode(language);
        return null;
    }

    public OfficeId getOfficeId() {
        return OfficeId.by().id(officeId);
    }

    public DateHelper getPickUpDate() {
        if (pickUp.getDate() != null) {
            return new DateHelper(pickUp.getDate(), "dd-MM-yyyy");
        }
        return null;
    }

    public DateHelper getPickupDateTime() {
        if (getPickUpDate() != null) {
            return getPickUpDate().addTime(pickUp.getTime());
        }
        return null;
    }

    public DateHelper getDropOffDate() {
        if (dropOff.getDate() != null) {
            return new DateHelper(dropOff.getDate(), "dd-MM-yyyy");
        }
        return null;
    }

    public DateHelper getDropOffDateTime() {
        if (getDropOffDate() != null) {
            return getDropOffDate().addTime(dropOff.getTime());
        }
        return null;
    }

    public DriverInfo getDriverInfo() {
        return driverInfo;
    }

    public int getRentalPeriod() {
        int from = getPickupDateTime().getDateTime().getDayOfMonth();
        int to = getDropOffDateTime().getDateTime().getDayOfMonth();
        return to - from;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]", env, client);
    }

    public static class Location {

        private String date;//dd-MM-yyyy

        private String time;// HH:mm

        private String location;

        private boolean isAirport;

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getLocation() {
            return location;
        }

        public boolean isAirport() {
            return isAirport;
        }
    }

    public static class Discounts {

        private final String provider;

        private final String type;

        private final String code;

        public Discounts(String provider, String type, String code) {
            this.provider = provider;
            this.type = type;
            this.code = code;
        }

        public String getProvider() {
            return provider;
        }

        public String getType() {
            return type;
        }

        public String getCode() {
            return code;
        }
    }

    public class DriverInfo {

        private int driverAge;
        private String firstName;
        private String lastName;
        private String phone;
        private String email;

        public int getDriverAge() {
            return driverAge;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }
    }
}
