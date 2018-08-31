package base.core.iata;


import base.core.constants.Dir;
import base.core.convertor.Serializable;
import base.core.dto.components.LocationDTO;
import base.core.exception.AutomatronException;
import base.core.helpers.FReader;
import base.core.utils.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IATA {

    private static IATA instance;
    private JSONArray airports;
    private JSONArray cities;

    private IATA() {
        airports = FReader.readJSONArray(Dir.Resources.MCP + "/airports.json");
        cities = FReader.readJSONArray(Dir.Resources.MCP + "/cities.json");

    }

    public static IATA getInstance() {
        if (instance == null) {
            instance = new IATA();
        }
        return instance;
    }

    /**
     * @param iataCode iata code of airport location
     * @return Airport deserialized object {@link Airport}
     */
    public static Airport airport(String iataCode) {
        IATA inst = getInstance();
        try {
            return inst.find(inst.airports, iataCode, Airport.class);
        } catch (AutomatronException e) {
            return null;
        }
    }

    /**
     * @param iataCode iata code of city location
     * @return City deserialized object {@link City}
     */
    public static City city(String iataCode) {
        IATA inst = getInstance();
        try {
            return inst.find(inst.cities, iataCode, City.class);
        } catch (AutomatronException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IATAEntity find(String location) throws AutomatronException {

        String[] parsed = location.split(",");
        if (parsed.length > 1) {
            return findCityByName(location);
        } else {
            return findAirportByName(location);
        }
    }

    public static City findCityByName(String location) throws AutomatronException {
        String[] parsed = location.split(",");
        for (Object o : getInstance().cities) {
            JSONObject object = (JSONObject) o;
            String capitalizedName = StringUtils.firstKeyToUpper(parsed[1]);
            try {
                if (object.getString("country").equals(parsed[0]) && object.getString("name").equals(capitalizedName)) {
                    return Serializable.deserialize(object, City.class);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        throw new AutomatronException(String.format("unable to locate city by name %s", location));
    }

    public static Airport findAirportByName(String name) throws AutomatronException {
        for (Object o : getInstance().airports) {
            JSONObject object = (JSONObject) o;
            try {
                if (object.getString("name").equals(name)) {
                    return Serializable.deserialize(object, Airport.class);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        throw new AutomatronException(String.format("unable to locate airport by name %s", name));
    }

    public static IATAEntity map(LocationDTO pickupLocation) {
        return Serializable.deserialize(Serializable.serialize(pickupLocation), IATAEntity.class);
    }

    private <T extends IATAEntity> T find(JSONArray data, String code, Class<T> iataClass) throws AutomatronException {
        for (Object o : data) {
            JSONObject object = (JSONObject) o;
            if (object.getString("code").equals(code)) {
                T entity = Serializable.deserialize(object, iataClass);
                entity.init();
                return entity;
            }
        }

        throw new AutomatronException(String.format("unable to find code %s for class %s", code, iataClass.getName()));
    }
}