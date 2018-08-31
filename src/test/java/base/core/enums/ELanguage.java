package base.core.enums;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhitnikov on 5/30/2017.
 */
public enum ELanguage {

    AR_AR("ar_AR", "العربية(قطر)", "AR", "ar"),
    DA_DK("da_DK", "DANSK", "DK", "da"),
    DE_DE("de_DE", "DEUTSCH", "DE", "de"),
    EN_GB("en_GB", "ENGLISH", "GB", "en"),
    EN_US("en_US", "ENGLISH(US)", "US", "en"),
    ES_ES("es_ES", "ESPAÑOL", "ES", "es"),
    FI_FI("fi_FI", "FINNISH", "FI", "fi"),
    FR_FR("fr_FR", "FRANÇAIS", "FR", "fr"),
    ID_ID("id_ID", "BAHASA INDONESIA", "ID", "id"),
    IT_IT("it_IT", "ITALIAN", "IT", "it"),
    JA_JP("ja_JP", "日本語", "JP", "ja"),
    KO_KR("ko_KR", "한국어", "KR", "ko"),
    MS_MY("ms_MY", "BAHASA MELAYU", "MY", "ms"),
    NB_NO("nb_NO", "NORSK", "NO", "nb"),
    PT_BR("pt_BR", "PORTUGAL", "BR", "pt"),
    SV_SE("sv_SE", "SVENSKA", "SE", "sv"),
    TH_TH("th_TH", "ไทย", "TH", "th"),
    ZH_HK("zh_HK", "中文 (香港)", "HK", "zh"),
    ZH_TW("zh_TW", "中文 (台灣)", "TW", "zh"),
    VI_VN("vi_VN", "TIẾNG VIỆT", "VN", "vi");

    public String code;
    public String text;
    public String country;
    public String lang;

    ELanguage(String code, String text, String country, String language) {
        this.code = code;
        this.text = text;
        this.country = country;
        this.lang = language;
    }

    public static ELanguage getByCode(String value) {
        for (ELanguage language : ELanguage.values()) {
            if (Objects.equals(language.code, value)) {
                return language;
            }
        }
        throw new EnumConstantNotPresentException(ELanguage.class, value);
    }

    public static ELanguage getByCountry(String value) {
        for (ELanguage language : ELanguage.values()) {
            if (Objects.equals(language.country, value)) {
                return language;
            }
        }
        throw new EnumConstantNotPresentException(ELanguage.class, value);

    }

    public static ELanguage getByText(String text) {
        for (ELanguage language : ELanguage.values()) {
            if (Objects.equals(language.text, text.toUpperCase())) {
                return language;
            }
        }
        throw new EnumConstantNotPresentException(ELanguage.class, text);
    }

    public static List<ELanguage> getList() {
        return Stream.of(ELanguage.values())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return this.text;
    }

}
