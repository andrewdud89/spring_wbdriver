package base.core.config;

import amadeus.cars.automatron.core.ConfigLoader;
import org.json.JSONObject;

public class OauthCredentials {

    public static final JSONObject OAUTH_DEV = new JSONObject() {{
        put("client_id", ConfigLoader.getInstance().getProperty("oauth.dev.client"));
        put("client_secret", ConfigLoader.getInstance().getProperty("oauth.dev.secret"));
        put("grant_type", "client_credentials");

    }};

    public static final JSONObject OAUTH_DEMO = new JSONObject() {{
        put("client_id", ConfigLoader.getInstance().getProperty("oauth.demo.client"));
        put("client_secret", ConfigLoader.getInstance().getProperty("oauth.demo.secret"));
        put("grant_type", "client_credentials");
    }};

    public static final JSONObject OAUTH_PROD = new JSONObject() {{
        put("client_id", ConfigLoader.getInstance().getProperty("oauth.prod.client"));
        put("client_secret", ConfigLoader.getInstance().getProperty("oauth.prod.secret"));
        put("grant_type", "client_credentials");
    }};

}
