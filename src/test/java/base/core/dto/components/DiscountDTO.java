package base.core.dto.components;

public class DiscountDTO {

    private final String providerCode;

    private final String type;

    private final String code;

    public DiscountDTO(String providerCode, String type, String code) {
        this.providerCode = providerCode;
        this.type = type;
        this.code = code;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }
}
