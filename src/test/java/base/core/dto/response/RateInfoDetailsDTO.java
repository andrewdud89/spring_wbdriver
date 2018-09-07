package base.core.dto.response;


import base.core.convertor.Serializable;
import base.core.dto.components.offer.OfferDTO;

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
