package base.core;

import base.core.config.OauthCredentials;
import base.core.http.HttpClient;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class OauthClient {

    private static final Logger log = Logger.getLogger(OauthClient.class);
    private String domain;
    private JSONObject credentials;

    public OauthClient(String domain) {

        try {
            URL url = new URL(domain);
            String[] container = url.getHost().split("\\.");
            String local = "oauth.carsbookingengine.com";
            credentials = OauthCredentials.OAUTH_PROD;
            if (container[0].contains("demo")) {
                local = "oauth-demo.carsbookingengine.com";
                credentials = OauthCredentials.OAUTH_DEMO;
            }
            if (container[0].contains("dev") || container[0].contains("stage")) {
                local = "oauth-dev.carsbookingengine.com";
                credentials = OauthCredentials.OAUTH_DEV;
            }

            this.domain = url.getProtocol() + "://" + local + "/oauth/v2/token";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getToken(Scope scope) {
        log.info("[OAUTH] Create token for " + domain);
        HttpClient http = new HttpClient();
        try {
            credentials.put("scope", scope.toString().toLowerCase());

            JSONObject response = http.post(domain, credentials).addHeader("Content-Type", "application/json").execute().toJSON();

            return response.getString("access_token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum Scope {
        API, TEASER
    }

}
