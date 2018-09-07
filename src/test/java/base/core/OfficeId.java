package base.core;

import base.core.constants.Dir;
import base.core.convertor.Serializable;
import base.core.helpers.FReader;
import base.core.iata.IATA;
import base.core.iata.IATAEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OfficeId extends Serializable {

    private String client;
    private String officeId;
    private String env;
    private String country;
    private String iata;
    private boolean isAirport;

    public static By by() {
        return new By();
    }

    public static ListOf list() {
        return new ListOf();
    }

    public IATAEntity getIATA() {
        if (isAirport) {
            return IATA.airport(iata);
        } else {
            return IATA.city(iata);
        }
    }

    public String getClient() {
        return client;
    }

    public String getValue() {
        return officeId;
    }

    public String getEnv() {
        return env;
    }

    public String getCountry() {
        return country;
    }

    public CarsEnvironment getEnvironment() {
        return CarsEnvironment.getInstance(client, env);
    }

    public static class By extends OfficeExtractor {

        public OfficeId id(String officeId) {
            for (Object datum : data) {
                JSONObject data = (JSONObject) datum;
                if (data.getString("officeId").equals(officeId)) {
                    return Serializable.deserialize(data, OfficeId.class);
                }
            }
            return null;
        }
    }

    public static class ListOf extends OfficeExtractor {

        public static List<OfficeId> byEnv(String env) {
            List<OfficeId> offices = new ArrayList<>();
            for (Object datum : data) {
                JSONObject data = (JSONObject) datum;
                if (data.getString("env").equals(env)) {
                    offices.add(Serializable.deserialize(data, OfficeId.class));
                }
            }
            return offices;
        }

        public List<OfficeId> byClient(String client) {
            List<OfficeId> offices = new ArrayList<>();
            for (Object datum : data) {
                JSONObject data = (JSONObject) datum;
                if (data.getString("client").equals(client)) {
                    offices.add(Serializable.deserialize(data, OfficeId.class));
                }
            }
            return offices;
        }

        public List<OfficeId> ByCountry(String country) {
            List<OfficeId> offices = new ArrayList<>();
            for (Object datum : data) {
                JSONObject data = (JSONObject) datum;
                if (data.getString("country").equals(country)) {
                    offices.add(Serializable.deserialize(data, OfficeId.class));
                }
            }
            return offices;
        }

        public List<OfficeId> all() {
            List<OfficeId> offices = new ArrayList<>();
            for (Object datum : data) {
                offices.add(Serializable.deserialize(datum, OfficeId.class));
            }
            return offices;
        }
    }

    static class OfficeExtractor {

        private static final String path = Dir.Resources.ADDITIONAL + "/officeId.json";
        static JSONArray data;

        static {
            data = FReader.readJSONArray(path);
        }

    }
}
