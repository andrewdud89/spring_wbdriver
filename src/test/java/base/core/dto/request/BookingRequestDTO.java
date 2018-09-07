package base.core.dto.request;

import base.core.convertor.Serializable;
import base.core.dto.components.DriverDetailDTO;
import base.core.dto.components.PaymentDTO;

import java.util.ArrayList;
import java.util.List;

public class BookingRequestDTO extends Serializable {
    private DriverDetailDTO driverDetails;

    private List<ExtraServices> extraServices;

    private String offerId;

    private PaymentDTO payment;

    private int tzOffset;

    public BookingRequestDTO() {
        extraServices = new ArrayList<>();
    }

    public DriverDetailDTO getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(DriverDetailDTO driverDetails) {
        this.driverDetails = driverDetails;
    }

    public List<ExtraServices> getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(List<ExtraServices> extraServices) {
        this.extraServices = extraServices;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public int getTzOffset() {
        return tzOffset;
    }

    public void setTzOffset(int tzOffset) {
        this.tzOffset = tzOffset;
    }


    public static class ExtraServices extends Serializable {
        private String code;

        private String type;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
