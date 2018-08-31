package base.core;

import base.core.constants.Dir;
import base.core.enums.ELanguage;
import base.core.helpers.FReader;
import base.core.translation.Translations;
import org.json.JSONObject;


/**
 * acriss parser
 * details @ resources/additional/acriss.json
 */
public class ACRISS {

    private static ACRISS instance;
    private char[] codeArray;
    private String code;
    private JSONObject lib;
    private ELanguage language;

    private ACRISS() {
        this.lib = FReader.readJSON(Dir.Resources.ADDITIONAL + "/acriss.json");
    }

    public static ACRISS getInstance() {
        if (instance == null) {
            instance = new ACRISS();
        }
        return instance;
    }

    public void set(String code) {
        this.code = code;
        this.codeArray = code.toCharArray();
    }

    public void setLanguage(ELanguage language) {
        Translations.getInstance().setLanguage(language);
    }

    // TODO change architecture using getVehicleClass, getType,
    // getTransmissionType, getTransmissionDrive, getFuel
//    public String getVehicleClass() {
//        JSONArray categories = lib.getJSONArray("vehicleClass");
//        for (Object o : categories) {
//            JSONObject item = (JSONObject) o;
//            String key = String.valueOf(codeArray[0]);
//            if (Objects.equals(key, item.get("code"))) {
//                return Translations.getInstance().translate(item.getString("name"));
//            }
//        }
//        return "";
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public String getType() {
//        JSONArray categories = lib.getJSONArray("types");
//        for (Object o : categories) {
//            JSONObject item = (JSONObject) o;
//            String key = String.valueOf(codeArray[1]);
//            if (Objects.equals(key, item.get("code"))) {
//                return Translations.getInstance().translate(item.getString("name"));
//            }
//        }
//        return "";
//    }
//
//    public String getTransmissionType() {
//        JSONArray categories = lib.getJSONArray("transmissionTypes");
//        for (Object o : categories) {
//            JSONObject item = (JSONObject) o;
//            String key = String.valueOf(codeArray[2]);
//            if (Objects.equals(key, item.get("code"))) {
//                return Translations.getInstance().translate(item.getString("name"));
//            }
//        }
//        return "";
//    }
//
//    public String getTransmissionDrive() {
//        JSONArray categories = lib.getJSONArray("transmissionDrives");
//        for (Object o : categories) {
//            JSONObject item = (JSONObject) o;
//            String key = String.valueOf(codeArray[2]);
//            if (Objects.equals(key, item.get("code"))) {
//                return Translations.getInstance().translate(item.getString("name"));
//            }
//        }
//        return "";
//    }
//
//    public String getFuel() {
//        JSONArray categories = lib.getJSONArray("fuel");
//        for (Object o : categories) {
//            JSONObject item = (JSONObject) o;
//            String key = String.valueOf(codeArray[3]);
//            if (Objects.equals(key, item.get("code"))) {
//                return Translations.getInstance().translate(item.getString("name"));
//            }
//        }
//        return "";
//    }

//    public JSONObject getData() {
//        return new JSONObject() {{
//            try {
//                put("category", getVehicleClass());
//                put("type", getType());
//                put("transmissionType", getTransmissionType());
//                put("transmissionDrive", getTransmissionDrive());
//                put("fuel", getFuel());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }};
//    }
}
