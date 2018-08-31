package base.core.dto.components;

import base.core.convertor.Serializable;

import java.util.List;

public class LocationDTO extends Serializable {
    protected String fullName;

    protected String type;

    protected String code;

    protected String longitude;

    protected String latitude;

    protected String address;

    protected String city;

    protected String country;

    protected String state;

    protected String countryCode;

    protected String countryname;

    protected String shuttleInfo;

    protected String name;

    protected boolean airport;

    private Contacts contacts;

    private WorkingHours workingHours;

    private List<String> terminalNames;

    public String getShuttleInfo() {
        return shuttleInfo;
    }

    public boolean isAirport() {
        return airport;
    }

    public String getFullName() {
        return fullName;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getName() {
        return name;
    }

    public String getCountryname() {
        return countryname;
    }


    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public List<String> getTerminalNames() {
        return terminalNames;
    }

    public static class Contacts {
        private String phone;
        private String fax;

        public String getPhone() {
            return phone;
        }

        public String getFax() {
            return fax;
        }
    }

    public static class WorkingHours {
        private String closingTime;
        private String openingTime;

        public String getClosingTime() {
            return closingTime;
        }

        public String getOpeningTime() {
            return openingTime;
        }
    }

}
