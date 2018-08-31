package base.core.dto.components.offer;

public class BrokerDTO {
    private final String code;

    private final String name;

    private final String logoUrl;

    public BrokerDTO(String code, String name, String logoUrl) {
        this.code = code;
        this.name = name;
        this.logoUrl = logoUrl;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
}