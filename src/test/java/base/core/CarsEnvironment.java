package base.core;

import base.core.constants.Dir;
import base.core.enums.ECarsClient;
import base.core.enums.ECarsEnvironment;
import base.core.helpers.FReader;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class CarsEnvironment {

    private final static Logger log = Logger.getLogger(CarsEnvironment.class);
    private static CarsEnvironment instance;
    private final JSONObject data;
    private final String client;
    private final String env;

    private CarsEnvironment(String client, String env) {
        data = FReader.readJSON(Dir.RESOURCES + "/envs.json");
        this.env = env;
        if (client == null) {
            client = "default";
        }
        this.client = client;
        log.info(String.format("Loading environment for [%s][%s]", env, client));
    }

    public static CarsEnvironment getInstance(String client, String env) {
        instance = new CarsEnvironment(client, env);
        return instance;
    }

    public static CarsEnvironment getInstance() {
        if (instance == null) {
            throw new NullPointerException("CarsEnvironment not initialized");
        }
        return instance;
    }

    public boolean isJenkinsRun() {
        return System.getenv(ConfigLoader.getInstance().getProperty("project.jenkins.data")) != null;
    }

    public String getApi() {
        try {
            return data.getJSONObject(env).getString("api");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSwitch() {
        try {
            return data.getJSONObject(env).getString("switch");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCDN() {
        try {
            return data.getJSONObject(env).getString("cdn");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getWhiteLabelUrl() {
        try {
            return data.getJSONObject(env).getJSONObject("wl").getString(client);
        } catch (JSONException e) {
            try {
                return data.getJSONObject(env).getJSONObject("wl").getString("default");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ECarsClient getClient() {
        return ECarsClient.valueOf(client.toUpperCase());
    }

    public ECarsEnvironment getEnvironment() {
        return ECarsEnvironment.valueOf(env.toUpperCase());
    }

    public String getWihteLabelUrlWithAuthoriztion() {
        String domain = null;
        try {
            domain = data.getJSONObject(env).getJSONObject("wl").getString(client);
        } catch (JSONException e) {
            try {
                domain = data.getJSONObject(env).getJSONObject("wl").getString("default");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }

        try {
            URIBuilder builder = new URIBuilder(domain);
            if (env.equals("prod") && !client.equals("sas")) {
                builder.setUserInfo(ConfigLoader.getInstance().getProperty("web.auth.user"), ConfigLoader.getInstance().getProperty("web.auth.password"));
            }
            return builder.toString();

        } catch (URISyntaxException e) {
            try {
                return data.getJSONObject(env).getJSONObject("wl").getString("default");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
