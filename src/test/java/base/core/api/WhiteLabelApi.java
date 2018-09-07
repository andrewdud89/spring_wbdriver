package base.core.api;


import base.core.OfficeId;
import base.core.convertor.Serializable;
import base.core.dto.ClientConfigDTO;
import base.core.dto.components.LocationDTO;
import base.core.dto.request.BookingRequestDTO;
import base.core.dto.request.SearchRequestDTO;
import base.core.dto.response.BookingCancelDTO;
import base.core.dto.response.BookingResponseDTO;
import base.core.dto.response.RateInfoDetailsDTO;
import base.core.dto.response.SearchResultDTO;
import base.core.enums.ELanguage;
import base.core.http.HttpClient;
import base.core.http.HttpClientHeader;
import base.core.utils.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WhiteLabelApi {

    private static final Logger log = Logger.getLogger(WhiteLabelApi.class);

    private static final String LANGUAGES_ROUTE = "/assets/i18n";
    private static final String IATA_DICTIONARY_ROUTE = "/dictionary/iata";
    private static final String SEARCH_ROUTE = "/search";
    private static final String OFFER_ROUTE = "/offer";
    private static final String OFFERS_ROUTE = "/offers";
    private static final String BOOKING_ROUTE = "/booking";
    private static final String RATE_INFO_ROUTE = "/rate_info_detail";
    private static final String MY_BOOKING_ROUTE = "/my_bookings";
    private static final String MY_BOOKING_CANCEL_ROUTE = "/cancel";

    private static final String X_PROCESS_ID_HEADER_KEY = "X-PROCESS-ID";

    private final HttpClient http;
    private final String domain;
    private CloseableHttpResponse lastResponse;
    private String lastProcessId;


    /**
     * <examples>
     * - [GET] https://dev.carsbookingengine.com/dictionary/iata/{iataCode} : Get IATA Entity from mcp
     * - [GET] https://dev.carsbookingengine.com/assets/i18n/{lang}.json : Get translations for language
     * - [POST] https://dev.carsbookingengine.com/search : search form submit
     * - [GET] https://dev.carsbookingengine.com/search{srid}/form : get search form details
     * - [GET] https://dev.carsbookingengine.com/search/{srid}/result/data : get search results by srid
     * - [GET] https://dev.carsbookingengine.com/rate_info_detail/{offerId} : get rate info details
     * - [POST] https://dev.carsbookingengine.com/booking booking : request submit
     * - [GET] https://dev.carsbookingengine.com/offers/{offerId}/termsUrl : [json] terms;
     * - [GET] https://dev.carsbookingengine.com/offers/{offerId}/terms : pretty [html] terms.
     * - [GET] https://dev.carsbookingengine.com/my_bookings?pnrLocator={PNR}&lastName={LastName} : booked car information
     * - [POST] https://dev.carsbookingengine.com/cancel  : cancel booking
     * </examples>
     */

    public WhiteLabelApi(String domain) {
        if (domain == null) {
            throw new NullPointerException("domain is null");
        }
        this.http = new HttpClient();
        if (!StringUtils.containsValues(domain, "dev", "stage", "demo")) {
            http.addHeader(HttpClientHeader.BASE_AUTHORIZATION_HEADER);
        }
        http.addHeader(HttpClientHeader.CONTENT_TYPE_APPLICATION_JSON)
                .addHeader(HttpClientHeader.X_REQUESTED_WITH_XML_HTTP_REQUEST);
        this.domain = domain;
    }

    //    /**
//     * get translations file data from whiteLabel assets
//     *
//     * @param language
//     * @return translations {@link JSONObject}
//     */
//    @Step("Get Translation for language {0}")
    public JSONObject getTranslation(ELanguage language) {
        http.get(domain + LANGUAGES_ROUTE + "/" + language.code + ".json")
                .execute();

        setResponseData();
        try {
            return http.toJSON();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    //    /**
//     * get iata locations list from whiteLabel iata controller
//     *
//     * @param iataCode {@link String}
//     * @return List of locations
//     */
//    @Step("Get IATA by code {0}")
    public List<LocationDTO> getIATA(String iataCode) {

        http.get(domain + IATA_DICTIONARY_ROUTE + "/" + iataCode)
                .execute();
        setResponseData();

        JSONArray list = null;
        try {
            list = new JSONArray(http.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<LocationDTO> dtoList = new ArrayList<>();

        for (Object o : list) {
            dtoList.add(Serializable.deserialize(o, LocationDTO.class));
        }
        return dtoList;
    }

    //    /**
//     * Send search request to whiteLabel search controller.
//     *
//     * @param searchFormDTO {@link SearchRequestDTO}
//     * @return search request id (srid)
//     */
//    @Step("Submit Search Request")
    public String search(SearchRequestDTO searchFormDTO) {
        JSONObject body = Serializable.serialize(searchFormDTO, true);
        http.post(domain + SEARCH_ROUTE, body)
                .execute();

        setResponseData();
        try {
            return http.toJSON().getString("srid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    /**
//     * get rates from whiteLabel search controller by request DTO
//     *
//     * @param srid {@link SearchRequestDTO}
//     * @return list of results
//     */
//    @Step("Get Search Params by srid: {0}")
    public SearchRequestDTO getSearchFormParams(String srid) {
        http.get(domain + SEARCH_ROUTE + "/" + srid + "/form")
                .execute();

        return deserializeResponse(SearchRequestDTO.class);
    }

    //    /**
//     * get rates from whiteLabel search controller
//     *
//     * @param srid {@link String}
//     * @return list of results
//     */
//    @Step("Get Search Params by srid: {0}")
    public SearchResultDTO getSearchResults(String srid) {
        http.get(domain + SEARCH_ROUTE + "/" + srid + "/result/data")
                .execute();

        return deserializeResponse(SearchResultDTO.class);
    }

    //    /**
//     * get RateInfo by offerId from whiteLabel rateInfo controller
//     *
//     * @param offerId {@link String}
//     * @return detailed rate info
//     */
//    @Step("Get Rate Info Details by offerId {0}")
    public RateInfoDetailsDTO getRateInfoDetails(String offerId) {
        http.get(domain + RATE_INFO_ROUTE + "/" + offerId)
                .execute();

        return deserializeResponse(RateInfoDetailsDTO.class);
    }

    //    /**
//     * loop request process to get search results by search request id from whiteLabel search controller
//     *
//     * @param srid                 {@link String}
//     * @param maxWaitTimeInSeconds {@link Integer}
//     * @return list of results
//     */
//    @Step("Get Search Results by srid: {0}")
    public SearchResultDTO getSearchResults(String srid, int maxWaitTimeInSeconds) {

        long breakTime = System.currentTimeMillis() + maxWaitTimeInSeconds * 1000;

        SearchResultDTO dto = null;
        while (System.currentTimeMillis() < breakTime) {
            dto = getSearchResults(srid);
            if (dto.isCompleted()) {
                return dto;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    //    /**
//     * get jsonObject of terms and contitions from whiteLabel offer controller
//     *
//     * @param offerId {@link String}
//     * @return JSONObject of terms and conditions
//     */
//    @Step("Get Terms Url by offerId: {0}")
    public JSONObject getTermsUrl(String offerId) {
        http.get(domain + OFFERS_ROUTE + "/" + offerId + "/termsUrl")
                .execute();

        setResponseData();
        try {
            return http.toJSON();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    /**
//     * get html terms and contditions from whiteLabel offer controller
//     *
//     * @param offerId {@link String}
//     * @return String context of terms and contitions
//     */
//    @Step("Get Terms by offerId: {0}")
    public String getTerms(String offerId) {
        http.get(domain + OFFERS_ROUTE + "/" + offerId + "/terms")
                .execute();

        setResponseData();
        return http.toString();
    }

    //
//    /**
//     * get list of booked offers from whiteLabel booking controller
//     *
//     * @param pnr      {@link String}
//     * @param lastName {@link String}
//     * @return list of booked offers
//     */
//    @Step("Get My Bookings by PNR: [{0}][{1}]")
    public List<BookingResponseDTO.Data> getMyBookings(String pnr, String lastName) {
        http.get(domain + MY_BOOKING_ROUTE + "?pnrLocator=" + pnr + "&lastName=" + lastName)
                .execute();

        JSONArray array = null;
        try {
            array = http.toJSON().getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setResponseData();
        List<BookingResponseDTO.Data> results = new ArrayList<>();
        for (Object o : array) {
            BookingResponseDTO.Data offer = Serializable.deserialize(o, BookingResponseDTO.Data.class);
            results.add(offer);
        }
        return results;
    }


    public ClientConfigDTO getClientConfig(OfficeId officeId) {
        return getClientConfig(officeId.getValue());
    }

    //    /**
//     * get clientConfig data by officeId
//     *
//     * @param officeId {@link String}
//     * @return ClientConfigEntity
//     */
//    @Step("Get Client Config")
    public ClientConfigDTO getClientConfig(String officeId) {
        String str;
        Pattern pattern;
        if (Objects.equals(officeId, "")) {
            pattern = Pattern.compile("window.clientConfig = (.*?);<", Pattern.DOTALL);
            str = domain;
        } else {
            pattern = Pattern.compile("window.clientConfig = (.*?);window", Pattern.DOTALL);
            str = domain + "/?officeId=" + officeId;
        }
        String response = http.get(str).execute().toString();

        setResponseData();

        Matcher m = pattern.matcher(response);
        m.find();
        try {
            return Serializable.deserialize(m.group(1), ClientConfigDTO.class);
        } catch (Exception e) {
            log.error("unable to find client config data in response");
            return null;
        }

    }

    public ClientConfigDTO getClientConfig() {
        return getClientConfig("");
    }

    //
//    /**
//     * send offer booking request to whiteLabel booking controller and get booking response
//     *
//     * @param bookingRequest {@link BookingRequestDTO}
//     * @return booking response
//     */
//    @Step("Book a Car")
    public BookingResponseDTO bookCar(BookingRequestDTO bookingRequest) {
        http.post(domain + BOOKING_ROUTE, Serializable.serialize(bookingRequest, false))
                .execute();

        return deserializeResponse(BookingResponseDTO.class);
    }


    //    /**
//     * send cancel booking reqeust (pnr_cancel) to whiteLabel myBookings controller
//     *
//     * @param confirmationNumber {@link String}
//     * @param pnrLocator         {@link String}
//     * @return result of operation
//     */
//    @Step("Cancel booking")
    public BookingCancelDTO pnrCancel(String confirmationNumber, String pnrLocator) {
        JSONObject rq = new JSONObject();
        rq.put("pnrLocator", pnrLocator).put("confirmationNumber", confirmationNumber);
        http.post(domain + MY_BOOKING_CANCEL_ROUTE, rq)
                .execute();

        return deserializeResponse(BookingCancelDTO.class);
    }

    public <T> T deserializeResponse(Class<T> dto) {
        int code = http.getResponse().getStatusLine().getStatusCode();
        if (code != 200) {
            throw new IllegalStateException(String.format("\nURL: %s\nUnexpected status code: %s\nMessage:%s", http.getURL(), code, http.toString()));
        }

        setResponseData();
        return Serializable.deserialize(http.toJSON(), dto);
    }

    private void setResponseData() {
        lastResponse = http.getResponse();
        setProcessId();
    }


    private void setProcessId() {
        Header[] headers = lastResponse.getHeaders(X_PROCESS_ID_HEADER_KEY);
        if (headers.length > 0)
            lastProcessId = headers[headers.length - 1].getValue();
    }

    public String getProcessId() {
        log.info(String.format("%s : %s", X_PROCESS_ID_HEADER_KEY, lastProcessId));
        return lastProcessId;
    }

    public CloseableHttpResponse getLastResponse() {
        return lastResponse;
    }
}
