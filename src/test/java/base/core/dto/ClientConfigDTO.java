package base.core.dto;


import base.core.convertor.Serializable;
import base.core.enums.ECarProviders;
import base.core.enums.ECurrency;
import base.core.enums.ELanguage;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClientConfigDTO extends Serializable {
    private int autocompleterAirportsLimit;

    private int autocompleterCitiesLimit;

    private List<Banners> banners;

    private boolean bannersEnabled;

    private String browserTitle;

    private List<String> currencies;

    private String defaultCurrency;

    private String defaultLanguage;

    private DiscountTypesPerProvider discountTypesPerProvider;

    private boolean discountsVisible;

    private int dropOffTime;

    private int fractionDigits;

    private String gaTrackerId;

    private Languages languages;

    private String officeId;

    private int pickUpTime;

    private List<Providers> providers;

    private int rentalPeriod;

    private int searchTimeout;

    private String theme;

    private List<TripStyles> tripStyles;

    private String url;

    public int getAutocompleterAirportsLimit() {
        return autocompleterAirportsLimit;
    }

    public int getAutocompleterCitiesLimit() {
        return autocompleterCitiesLimit;
    }

    public List<Banners> getBanners() {
        return banners;
    }

    public boolean isBannersEnabled() {
        return bannersEnabled;
    }

    public String getBrowserTitle() {
        return browserTitle;
    }

    public List<ECurrency> getCurrencies() {

        List<ECurrency> currencies = new ArrayList<>();

        for (String currency : this.currencies) {
            currencies.add(ECurrency.getByText(currency));
        }
        return currencies;
    }

    public ECurrency getDefaultCurrency() {
        return ECurrency.getByText(defaultCurrency);
    }

    public ELanguage getDefaultLanguage() {
        return ELanguage.getByCode(defaultLanguage);
    }

    public DiscountTypesPerProvider getDiscountTypesPerProvider() {
        return discountTypesPerProvider;
    }

    public boolean isDiscountsVisible() {
        return discountsVisible;
    }

    public int getDropOffTime() {
        return dropOffTime;
    }

    public int getFractionDigits() {
        return fractionDigits;
    }

    public String getGaTrackerId() {
        return gaTrackerId;
    }

    public List<ELanguage> getLanguages() {
        List<ELanguage> langs = new ArrayList<>();
        for (String s : languages.getFields()) {
            langs.add(ELanguage.getByCode(s));
        }
        return langs;
    }

    public String getOfficeId() {
        return officeId;
    }

    public int getPickUpTime() {
        return pickUpTime;
    }

    public List<Providers> getProviders() {
        return providers;
    }

    public int getRentalPeriod() {
        return rentalPeriod;
    }

    public int getSearchTimeout() {
        return searchTimeout;
    }

    public String getTheme() {
        return theme;
    }

    public List<TripStyles> getTripStyles() {
        return tripStyles;
    }

    public String getUrl() {
        return url;
    }

    public List<ECarProviders> getProvidersEnum() {
        List<ECarProviders> list = new ArrayList<>();
        for (Providers provider : providers) {
            list.add(ECarProviders.getProviderByCode(provider.code));
        }
        return list;
    }

    public static class Banners {
        private String type;

        public String getType() {
            return type;
        }
    }

    public static class DiscountTypesPerProvider {
        private List<DiscountTypes> sx;

        private List<DiscountTypes> zd;

        private List<DiscountTypes> ze;

        private List<DiscountTypes> zi;

        private List<DiscountTypes> zt;

        @SerializedName("default")
        private List<DiscountTypes> aDefault;

        public List<DiscountTypes> getSx() {
            return sx;
        }

        public List<DiscountTypes> getZd() {
            return zd;
        }

        public List<DiscountTypes> getZe() {
            return ze;
        }

        public List<DiscountTypes> getZi() {
            return zi;
        }

        public List<DiscountTypes> getZt() {
            return zt;
        }

        public List<DiscountTypes> getDefault() {
            return aDefault;
        }

        public static class DiscountTypes {
            private String title;

            private String type;

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }
        }
    }

    public static class Languages extends Serializable {

        @SerializedName("ar_AR")
        private String ar_AR;

        @SerializedName("da_DK")
        private String da_DK;

        @SerializedName("de_DE")
        private String de_DE;

        @SerializedName("en_GB")
        private String en_GB;

        @SerializedName("en_US")
        private String en_US;

        @SerializedName("es_ES")
        private String es_ES;

        @SerializedName("fi_FI")
        private String fi_FI;

        @SerializedName("fr_FR")
        private String fr_FR;

        @SerializedName("id_ID")
        private String id_ID;

        @SerializedName("it_IT")
        private String it_IT;

        @SerializedName("ja_JP")
        private String ja_JP;

        @SerializedName("ko_KR")
        private String ko_KR;

        @SerializedName("ms_MY")
        private String ms_MY;

        @SerializedName("nb_NO")
        private String nb_NO;

        @SerializedName("pt_BR")
        private String pt_BR;

        @SerializedName("sv_SE")
        private String sv_SE;

        @SerializedName("th_TH")
        private String th_TH;

        @SerializedName("vi_VN")
        private String vi_VN;

        @SerializedName("zh_HK")
        private String zh_HK;

        @SerializedName("zh_TW")
        private String zh_TW;

        public List<String> getFields() {
            List<String> result = new ArrayList<>();
            Field[] fields = getClass().getDeclaredFields();

            for (Field field : fields) {
                try {
                    String name = field.getName();
                    Object val = field.get(this);
                    if (val != null) {
                        result.add(name);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

    public static class Providers {
        private int bonusMiles;

        private String code;

        private List<Object> discounts;

        private String logoUrl;

        private String name;

        public int getBonusMiles() {
            return bonusMiles;
        }

        public String getCode() {
            return code;
        }

        public List<Object> getDiscounts() {
            return discounts;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public String getName() {
            return name;
        }

        public ECarProviders getProvider() {
            return ECarProviders.getProviderByCode(code);
        }
    }

    public static class TripStyles {
        private List<String> carTypes;

        private List<String> mileage;

        private String name;

        private List<String> passengers;

        public List<String> getCarTypes() {
            return carTypes;
        }

        public List<String> getMileage() {
            return mileage;
        }

        public String getName() {
            return name;
        }

        public List<String> getPassengers() {
            return passengers;
        }
    }

}
