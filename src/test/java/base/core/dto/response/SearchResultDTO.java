package base.core.dto.response;

import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.offer.OfferDTO;
import amadeus.core.convertor.Serializable;

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
