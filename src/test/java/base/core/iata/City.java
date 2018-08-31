package base.core.iata;

import base.core.dto.components.LocationDTO;


/**
 * defined City class of IataDTO {@link LocationDTO}
 */
public class City extends IATAEntity {


    public City() {
        super();
        isAirport(false);
    }

    @Override
    public void init() {
        setCity(name);
        countryCode = country;
        country = countryname;
        fullName = getFullName();
    }

    public String getFullName() {
        return getName() + ", " + getCountryname();
    }

}
