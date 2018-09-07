package base.core.dto.response;


import base.core.convertor.Serializable;
import base.core.dto.components.DriverDetailDTO;
import base.core.dto.components.LocationDTO;
import base.core.dto.components.ProviderDTO;
import base.core.dto.components.offer.MileageIncludedDTO;
import base.core.dto.components.offer.VehicleDTO;
import base.core.dto.components.rate.ChargeDTO;
import base.core.dto.components.rate.PriceBreakDownDTO;

import java.util.List;

public class BookingResponseDTO extends Serializable {
    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data extends Serializable {

        private String pnrLocator;

        private OrderDTO order;

        private DriverDetailDTO traveler;

        private String currency;

        private double currencyExchange;

        private PriceBreakDownDTO priceBreakDown;

        private List<ChargeDTO> rateIncludes;

        private List<ChargeDTO> specialEquipment;

        public String getPnrLocator() {
            return pnrLocator;
        }

        public OrderDTO getOrder() {
            return order;
        }

        public DriverDetailDTO getTraveler() {
            return traveler;
        }

        public String getCurrency() {
            return currency;
        }

        public double getCurrencyExchange() {
            return currencyExchange;
        }

        public PriceBreakDownDTO getPriceBreakDown() {
            return priceBreakDown;
        }

        public List<ChargeDTO> getRateIncludes() {
            return rateIncludes;
        }

        public List<ChargeDTO> getSpecialEquipment() {
            return specialEquipment;
        }

        public static class OrderDTO {
            private String confirmationNumber;

            private String offerID;

            private String reservationDate;

            private VehicleDTO vehicle;

            private LocationDTO pickupLocation;

            private ProviderDTO provider;

            private String pickupDate;

            private LocationDTO dropoffLocation;

            private String dropoffDate;

            private String paymentType;

            private MileageIncludedDTO mileageIncluded;

            public String getConfirmationNumber() {
                return confirmationNumber;
            }

            public String getReservationDate() {
                return reservationDate;
            }

            public VehicleDTO getVehicle() {
                return vehicle;
            }

            public LocationDTO getPickupLocation() {
                return pickupLocation;
            }

            public ProviderDTO getProvider() {
                return provider;
            }

            public String getPickupDate() {
                return pickupDate;
            }

            public LocationDTO getDropoffLocation() {
                return dropoffLocation;
            }

            public String getDropoffDate() {
                return dropoffDate;
            }

            public String getPaymentType() {
                return paymentType;
            }

            public MileageIncludedDTO getMileageIncluded() {
                return mileageIncluded;
            }
        }
    }
}
