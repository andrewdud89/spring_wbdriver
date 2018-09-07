package base.core.config;

import base.core.ConfigLoader;
import org.json.JSONException;
import org.json.JSONObject;

public class OauthCredentials {

    public static final JSONObject OAUTH_DEV = new JSONObject() {{
        try {
            put("client_id", ConfigLoader.getInstance().getProperty("oauth.dev.client"));
            put("client_secret", ConfigLoader.getInstance().getProperty("oauth.dev.secret"));
            put("grant_type", "client_credentials");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }};

    public static final JSONObject OAUTH_DEMO = new JSONObject() {{
        try {
            put("client_id", ConfigLoader.getInstance().getProperty("oauth.demo.client"));
            put("client_secret", ConfigLoader.getInstance().getProperty("oauth.demo.secret"));
            put("grant_type", "client_credentials");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }};

    public static final JSONObject OAUTH_PROD = new JSONObject() {{
        try {
            put("client_id", ConfigLoader.getInstance().getProperty("oauth.prod.client"));
            put("client_secret", ConfigLoader.getInstance().getProperty("oauth.prod.secret"));
            put("grant_type", "client_credentials");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }};

}
