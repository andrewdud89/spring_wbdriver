package base.core;

import amadeus.cars.automatron.core.constants.Dir;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {


    private static final String path = Dir.RESOURCES + "/project.properties";
    private static ConfigLoader instance;
    private Properties properties;

    private ConfigLoader() {
        properties = new Properties();
        try {
            properties.load(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance.properties;
    }


}
