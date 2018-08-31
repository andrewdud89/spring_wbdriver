package base.core.dto.components.rate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PriceBreakDownDTO {
    private ChargeDTO total;

    private List<ChargeDTO> rates;

    private List<ChargeDTO> charges;

    public ChargeDTO getTotal() {
        return total;
    }

    public List<ChargeDTO> getRates() {
        if (rates == null) {
            rates = new ArrayList<>();
        }
        return rates;
    }

    public List<ChargeDTO> getCharges() {

        if (charges == null) {
            charges = new ArrayList<>();
        }
        return charges;
    }

    /**
     * @return charges with amount == 0
     */
    public List<ChargeDTO> getRateIncludes() {
        return charges.stream().filter(chargeDTO -> chargeDTO.getAmount() == 0).collect(Collectors.toList());
    }


    /**
     * @return charges with amount > 0 and not a base rate
     */
    public List<ChargeDTO> getIncludedCharges() {
        List<ChargeDTO> includedCharges = charges.stream().filter(chargeDTO -> chargeDTO.getAmount() > 0).collect(Collectors.toList());
        includedCharges.removeIf(chargeDTO -> chargeDTO.getType().equals("RB")); // remove baseRate;
        return includedCharges;
    }

    /**
     * @return BaseRate charge{@link ChargeDTO} || {@code null}
     */
    public ChargeDTO getBaseRate() {
        return charges.stream().filter(chargeDTO -> chargeDTO.getType().equals("RB")).findFirst().orElse(null);
    }
}
