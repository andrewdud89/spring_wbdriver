package base.core.dto.components.offer;

public class MultimediaDTO {
    private final String type;

    private final String url;

    public MultimediaDTO(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}