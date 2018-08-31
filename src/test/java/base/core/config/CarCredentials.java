package base.core.config;

import base.core.ConfigLoader;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.Base64;

public class CarCredentials {


    public static String getAuthBase64() {
        return Base64.getEncoder().encodeToString(
                (ConfigLoader.getInstance().getProperty("web.auth.user") + ":" + ConfigLoader.getInstance().getProperty("web.auth.password")).getBytes()
        );
    }

    public static Header getAuthHeader() {
        return new BasicHeader("Authorization", "Basic " + CarCredentials.getAuthBase64());
    }
}
