package base.core.api;

import base.core.constants.Domain;
import base.core.enums.ECurrency;
import base.core.http.HttpClient;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by zhitnikov on 6/6/2017.
 */
public class McpApi {
    private static final Logger log = Logger.getLogger(McpApi.class);
    private static final String VERSION = "/v1/mcp";
    private static final String CURRENCTY = "/currency";
    private static final String IATA_AIRPORTS = "/iata/airports";
    private static final String IATA_CITIES = "/iata/cities";
    private static final String FORMAT_JSON = "?_format=json";

    private String domain = Domain.MCP.DEMO;
    private HttpClient http;

    public McpApi(String apiUrl) {
        domain = apiUrl;
        http = new HttpClient();
    }

    /**
     * This method is used to get currency rates from mcp container
     *
     * @param currency ECurrency code
     * @return result as JSONArray
     */
    public JSONArray getCurrencies(ECurrency currency) {

        http.get(domain + VERSION + CURRENCTY + "/" + currency.code)
                .execute();
        try {
            return new JSONArray(http.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * This method is used to get iata airport data from mcp container
     *
     * @return result as JSONArray
     */
    public JSONArray getIataAirports() {

        http.get(domain + VERSION + IATA_AIRPORTS)
                .execute();
        try {
            return new JSONArray(http.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to get iata city data from mcp container
     *
     * @return result as JSONArray
     */
    public JSONArray getIataCities() {
        http.get(domain + VERSION + IATA_CITIES)
                .execute();
        try {
            return new JSONArray(http.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
