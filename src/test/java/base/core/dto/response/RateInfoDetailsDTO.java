package base.core.dto.response;

import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.offer.OfferDTO;
import amadeus.core.convertor.Serializable;

public class RateInfoDetailsDTO extends Serializable {


    private OfferDTO offer;

    private String srid;

    public OfferDTO getOffer() {
        return offer;
    }

    public String getSrid() {
        return srid;
    }

}
