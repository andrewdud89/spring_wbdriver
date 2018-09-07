package base.core.dto.response;


import base.core.convertor.Serializable;
import base.core.dto.components.offer.OfferDTO;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDTO extends Serializable {

    private List<OfferDTO> offers;
    private boolean completed;

    public List<OfferDTO> getOffers() {
        if (offers == null) {
            offers = new ArrayList<>();
        }
        return offers;
    }

    public boolean isCompleted() {
        return completed;
    }
}
