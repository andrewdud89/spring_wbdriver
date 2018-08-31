package base.core.translation;

import amadeus.cars.automatron.core.constants.Dir;
import base.core.enums.ELanguage;
import base.core.helpers.FReader;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Translations {

    private static final Logger log = Logger.getLogger(Translations.class);
    private static Translations instance;
    private String file;
    private JSONObject data;
    private ELanguage language;

    private Translations() {

    }

    public static Translations getInstance() {
        if (instance == null) {
            instance = new Translations();
        }
        return instance;
    }

    /**
     * Translate code to [lang] text
     *
     * @param code {@link String}
     * @return translated code
     */
    public String translate(String code) {
        if (data == null) {
            log.error("[Translations] translations file not set");
            return code;
        }
        try {
            return data.getString(code.trim()).trim();
        } catch (JSONException e) {
            return code;
        }
    }

    /**
     * get code from translated text
     *
     * @param text {@link String}
     * @return code of translated text
     */
    public String getCode(String text) {
        if (data == null) {
            log.error("[Translations] translations file not set");
            return text;
        }
        Set<String> set = data.keySet();
        for (String code : set) {
            String result = data.getString(code.trim()).trim();
            if (result.replaceAll(" ", " ").equals(text.trim())) {
                return code.trim();
            }
        }
//        log.warn("[Translations] Unable to find code for text '" + text + "' in translations file " + file);
        return text;
    }

    public List<String> getCodes(String text) {
        List<String> codes = new ArrayList<>();
        if (data == null) {
            log.error("[Translations] translations file not set");
            return codes;
        }
        Set<String> set = data.keySet();
        for (String code : set) {
            String result = data.getString(code.trim()).trim();
            if (result.replaceAll(" ", " ").equals(text.trim())) {
                codes.add(code.trim());
            }
        }
        return codes;
    }

    public boolean codeExist(String code) {
        if (data == null) {
            log.error("[Translations] translations file not set");
            return false;
        }
        return data.has(code);
    }

    public boolean textExist(String text) {
        if (data == null) {
            log.error("[Translations] translations file not set");
            return false;
        }
        Set<String> set = data.keySet();
        for (String code : set) {
            if (Objects.equals(data.getString(code), text)) {
                return true;
            }
        }
        return false;
    }

    public ELanguage getLanguage() {
        return language;
    }

    public Translations setLanguage(ELanguage language) {
        this.language = language;
        this.file = language.code + ".json";
        data = FReader.readJSON(Dir.Resources.TRANSLATIONS + "/" + file);
        log.info("[Translations] Set dictionary for " + language.code);
        return this;
    }

    public DateTimePattern getPattern() {
        return new DateTimePattern(language);
    }

    public List<String> getAll() {
        List<String> langs = new ArrayList<>();
        File dir = new File(Dir.Resources.TRANSLATIONS);
        File[] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                langs.add(file.getName());
            }
        }
        return langs;
    }
}
