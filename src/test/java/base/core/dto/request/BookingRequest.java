package base.core.dto.request;

import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.DriverDetailDTO;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.PaymentDTO;
import amadeus.cars.automatron.carsbookingengine.whiteLabel.dto.components.rate.ChargeDTO;

public class BookingRequest {

    private BookingRequestDTO request;

    private DriverDetailDTO driverDetails;
    private PaymentDTO payement;
    private String offerId;
    private ChargeDTO[] extras;

    private BookingRequest() {
        request = new BookingRequestDTO();
    }

    public static Builder builder() {
        return new BookingRequest().new Builder();
    }

    public class Builder {

        public Builder setDriverDetails(DriverDetailDTO driverDetails) {
            BookingRequest.this.driverDetails = driverDetails;
            return this;
        }

        public Builder setPaymentDetails(PaymentDTO payment) {
            BookingRequest.this.payement = payment;
            return this;
        }

        public Builder setOfferId(String offerId) {
            BookingRequest.this.offerId = offerId;
            return this;
        }

        public Builder setExtras(ChargeDTO... extras) {
            BookingRequest.this.extras = extras;
            return this;
        }

        public BookingRequestDTO build() {
            if (driverDetails == null) {
                throw new NullPointerException("driver details is mandatory object");
            }
            if (offerId == null) {
                throw new NullPointerException("offerId not set");
            }

            request.setDriverDetails(driverDetails);
            request.setOfferId(offerId);

            if (extras != null) {
                for (ChargeDTO extra : extras) {
                    BookingRequestDTO.ExtraServices extrasService = new BookingRequestDTO.ExtraServices();
                    extrasService.setCode(extra.getCode());
                    extrasService.setType(extra.getType());
                    request.getExtraServices().add(extrasService);
                }
            }

            if (payement != null) {
                request.setPayment(payement);
            }

            request.setTzOffset(-180);
            return request;
        }
    }
}
