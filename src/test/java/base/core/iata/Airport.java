package base.core.iata;


import base.core.dto.components.LocationDTO;

/**
 * defined Airport class of IataDTO {@link LocationDTO}
 */
public class Airport extends IATAEntity {

    public Airport() {
        super();
        isAirport(true);
    }

    @Override
    public void init() {
        fullName = getFullName();
        countryCode = country;
        country = countryname;
    }

    public String getFullName() {
        return getName() + ", " + getCity() + ", " + getCountryname();
    }
}
