package base.core.iata;


import base.core.dto.components.LocationDTO;

public abstract class IATAEntity extends LocationDTO {


    public IATAEntity() {
        setType("IATA");
    }

    public abstract void init();

    void isAirport(boolean isAirport) {
        this.airport = isAirport;
    }

    void setType(String type) {
        this.type = type;
    }

    void setCity(String city) {
        this.city = city;
    }
}
