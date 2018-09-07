package base.core.dto;

import base.core.convertor.Serializable;

public class FrontendConfigDTO extends Serializable {
    private String wsUrl;

    private int pollingResultMaxAttempt;

    private int extrasInCartMaxCount;

    private int childSeatsExtrasInCartMaxCount;

    private String searchRequestUrl;

    private String offersPollingResultUrl;

    private String searchFormDataUrl;

    private String bookingRequestUrl;

    private String rateDetailsFromAvailabilityRequestUrl;

    private String bookingCancellationUrl;

    private String myBookingsUrl;

    private String iataSearchUrl;

    private String offerUrl;

    private String offerSridUrl;

    private String termsUrl;

    private String currentLang;

    private String sentryDsn;

    private String offerImageFallbackUrl;

    public String getWsUrl() {
        return wsUrl;
    }

    public int getPollingResultMaxAttempt() {
        return pollingResultMaxAttempt;
    }

    public int getExtrasInCartMaxCount() {
        return extrasInCartMaxCount;
    }

    public int getChildSeatsExtrasInCartMaxCount() {
        return childSeatsExtrasInCartMaxCount;
    }

    public String getSearchRequestUrl() {
        return searchRequestUrl;
    }

    public String getOffersPollingResultUrl() {
        return offersPollingResultUrl;
    }

    public String getSearchFormDataUrl() {
        return searchFormDataUrl;
    }

    public String getBookingRequestUrl() {
        return bookingRequestUrl;
    }

    public String getRateDetailsFromAvailabilityRequestUrl() {
        return rateDetailsFromAvailabilityRequestUrl;
    }

    public String getBookingCancellationUrl() {
        return bookingCancellationUrl;
    }

    public String getMyBookingsUrl() {
        return myBookingsUrl;
    }

    public String getIataSearchUrl() {
        return iataSearchUrl;
    }

    public String getOfferUrl() {
        return offerUrl;
    }

    public String getOfferSridUrl() {
        return offerSridUrl;
    }

    public String getTermsUrl() {
        return termsUrl;
    }

    public String getCurrentLang() {
        return currentLang;
    }

    public String getSentryDsn() {
        return sentryDsn;
    }

    public String getOfferImageFallbackUrl() {
        return offerImageFallbackUrl;
    }
}
