package base.core.http;


import base.core.config.CarCredentials;

public enum HttpClientHeader {
    CONTENT_TYPE_APPLICATION_JSON("Content-type", "application/json"),
    CONTENT_TYPE_APPLICATION_TEXT_HTML("Content-type", "text/html"),
    CONTENT_TYPE_APPLICATION_XML("Content-type", "application/xml"),
    BASE_AUTHORIZATION_HEADER("Authorization", "Basic " + CarCredentials.getAuthBase64()),
    X_REQUESTED_WITH_XML_HTTP_REQUEST("X-Requested-With", "XMLHttpRequest");

    public String key;
    public String value;

    HttpClientHeader(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
