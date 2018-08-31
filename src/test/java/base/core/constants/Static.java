package base.core.constants;

/**
 * Static data for tests usage
 */
public class Static {

    /**
     * Static Credentials data
     */
    public static class Credentials {

        public static final String FLIGHT_NUMBER = "LOL111";

        /**
         * driver details
         */
        public static class Driver {
            public static final String FIRST_NAME = "Mister";
            public static final String LAST_NAME = "AutoTester";
            public static final String NAME_TITLE = "MR";
            public static final String EMAIL = "Andrii.DUDCHENKO@amadeus.com";
            public static final String PHONE = "+380501900126";
            public static final int AGE = 32;
        }

        /**
         * payment card data
         */
        public static class PaymentCard {
            public static final String NUMBER = "4111111111111111";
            public static final String HODLER = "First Lastname";
            public static final String FIRST_NAME = "First";
            public static final String LAST_NAME = "Lastname";
            public static final String EXPIRATION_DATE = "1218";
            public static final String EXPIRATION_MONTH = "12";
            public static final String EXPIRATION_YEAR = "18";
            public static final String CVV = "123";
            public static final String CODE = "VI";

        }
    }

    public static class Mongo {
        public static class Dev {
            public static String host = "dev.carsbookingengine.com";
            public static int port = 27017;
            public static String database = "local";
        }

        public static class Stage {
            public static String host = "dev.carsbookingengine.com";
            public static int port = 27018;
            public static String database = "local";
        }
    }
}
