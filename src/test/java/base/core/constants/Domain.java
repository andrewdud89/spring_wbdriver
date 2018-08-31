package base.core.constants;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhitnikov on 5/30/2017.
 */
public class Domain {

    public static final int DEV_MONGO_PORT = 27017;
    public static final int STAGE_MONGO_PORT = 27018;
    public static final int REDIS_PORT = 6379;

    public static class WhiteLabel {
        //WL
        public static final String DEV = "https://dev.carsbookingengine.com";
        public static final String DEV2 = "https://dev2.carsbookingengine.com";

        public static final String DEV_MAS = "https://dev-mas.carsbookingengine.com";
        public static final String DEV_SAS = "https://dev-sas.carsbookingengine.com";
        public static final String DEV_LATAM = "https://dev-latam.carsbookingengine.com";
        public static final String DEV_FLYBE = "https://dev-flybe.carsbookingengine.com";

        public static final String STAGE = "https://stage.carsbookingengine.com";

        public static final String DEMO = "https://demo.carsbookingengine.com";
        public static final String DEMO_MAS = "https://demo-mas.carsbookingengine.com";
        public static final String DEMO_SAS = "https://demo-sas.carsbookingengine.com";

        public static final String PROD_GREEN = "https://green.carsbookingengine.com";
        public static final String PROD_BLUE = "https://blue.carsbookingengine.com";
        public static final String PROD = "https://carsbookingengine.com";
        public static final String PROD_MAS = "https://mas.carsbookingengine.com";
        public static final String PROD_SAS = "https://sas.carsbookingengine.com";

    }

    public static class MCP {
        public static final String DEV = "https://dev-mcp.transferbookingengine.com/api";
        public static final String STAGE = "https://stage-mcp.transferbookingengine.com/api";
        public static final String DEMO = "https://demo-mcp.transferbookingengine.com/api";
    }

    public static class API {
        public static final String DEV = "https://dev-api.carsbookingengine.com";
        public static final String STAGE = "https://stage-api.carsbookingengine.com";
        public static final String DEMO = "https://demo-api.carsbookingengine.com";
        public static final String PROD = "https://api.carsbookingengine.com";
        public static final String PROD_BLUE = "https://api-blue.carsbookingengine.com";
        public static final String PROD_GREEN = "https://api-green.carsbookingengine.com";
    }

    public static class Switch {

        public static URL BLUE;
        public static URL GREEN;
        public static URL DEV;
        public static URL STAGE;
        public static URL DEMO;
        public static URL PROD;

        static {
            try {
                DEV = new URL("https://dev-switch.carsbookingengine.com/api/soap/VehicleService?wsdl");
                STAGE = new URL("https://stage-switch.carsbookingengine.com/api/soap/VehicleService?wsdl");
                DEMO = new URL("https://demo-switch.carsbookingengine.com/api/soap/VehicleService?wsdl");
                PROD = new URL("https://switch.carsbookingengine.com/api/soap/VehicleService?wsdl");
                BLUE = new URL("https://blue-switch.carsbookingengine.com/api/soap/VehicleService?wsdl");
                GREEN = new URL("https://green-switch.carsbookingengine.com/api/soap/VehicleService?wsdl");


            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public static class CDN {

        public static final String DEV = "https://dev-cdn.carsbookingengine.com/";
        public static final String STAGE = "https://stage-cdn.carsbookingengine.com/";
        public static final String DEMO = "https://demo-cdn.carsbookingengine.com/";
        public static final String BLUE = "https://cdn-blue.carsbookingengine.com/";
        public static final String GREEN = "https://cdn-green.carsbookingengine.com/";
        public static final String PROD = "https://cdn.carsbookingengine.com/";

    }

}
