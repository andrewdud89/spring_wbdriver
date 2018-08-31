package base.core.enums;

import java.util.Objects;

/**
 * Created by zhitnikov on 5/31/2017.
 */
public enum ECarProviders {
    SIXT("SX", "SIXT", "COR", 0),
    AVIS("ZI", "AVIS RENT-A-CAR", "COR", 1),
    HERTZ("ZE", "HERTZ", "ALL", 2),
    EUROPCAR("EP", "EUROPCAR", "COR", 3),
    DOLLAR("ZR", "DOLLAR", "COR", 4),
    BUDGET("ZD", "BUDGET", "COR", 5),
    ENTERPRISE("ET", "ENTERPRISE", "COR", 6),
    THRIFTY("ZT", "THRIFTY", "COR", 7),
    CITER("CI", "CITER", "COR", 8),
    ALAMO("AL", "ALAMO", "COR", 9),
    EUROMOBIL("EM", "EUROMOBIL", "COR", 10),
    LOCALIZA("LL", "LOCALIZA", "COR", 11),
    MOVIDA("MO", "MOVIDA", "COR", 12),
    UNIDAS("UN", "UNIDAS", "COR", 13),
    PAYLESS("ZA", "PAYLESS", "COR", 14),
    RENTAL_CARS("RS", "RENTAL CARS", "LEI", 15),
    FIREFLY("FF", "FIREFLY", "COR", 16),
    ECONOMY("EY", "ECONOMY", "COR", 17),
    NATIONAL("ZL", "NATIONAL", "COR", 18),
    ADVANTAGE("AD", "ADVANTAGE", "ALL", 19),
    BIDVEST("BV", "BIDVEST", "ALL", 20),
    AUTOEUROPE("ZU", "AUTOEUROPE", "ALL", 21),
    EZ_RENT_A_CAR("EZ", "EZ RENT A CAR", "ALL", 22),
    FIRST_CAR_RENTAL("FC", "FIRST CAR RENTAL", "ALL", 23),
    MAGGIORE("MG", "MAGGIORE", "ALL", 24),
    U_SAVE("SV", "U-SAVE", "ALL", 25),
    WOODFORD("WF", "WOODFORD", "ALL", 26),
    EXPRESS("EX", "EXPRESS", "ALL", 27),
    EUROPCAR_SOUTH_AFRICA("IM", "EUROPCAR SOUTH AFRICA", "ALL", 28),
    FOX_RENT_A_CAR("FX", "FOX RENT A CAR", "ALL", 29),
    CARO("CR", "CARO", "ALL", 30);
//    NULL("TEST ITEM",           "NU", "NULL", "COR");

    public String code;
    public String title;
    public String awsRateClass;
    public int priority;

    ECarProviders(String code, String title, String awsRateClass, int priority) {
        this.code = code;
        this.title = title;
        this.awsRateClass = awsRateClass;
        this.priority = priority;
    }

    public static boolean providerCodeExist(String companyCode) {
        for (ECarProviders provider : ECarProviders.values()) {
            if (Objects.equals(provider.code, companyCode)) {
                return true;
            }
        }
        return false;
    }

    public static ECarProviders getProviderByCode(String code) {
        for (ECarProviders provider : ECarProviders.values()) {
            if (Objects.equals(provider.code, code)) {
                return provider;
            }
        }
        throw new EnumConstantNotPresentException(ECarProviders.class, code);
    }

    public static ECarProviders getProviderByTitle(String title) {
        for (ECarProviders provider : ECarProviders.values()) {
            if (Objects.equals(provider.title, title)) {
                return provider;
            }
        }
        throw new EnumConstantNotPresentException(ECarProviders.class, title);

    }

    @Override
    public String toString() {
        return code + "(" + title + ")";
    }
}
