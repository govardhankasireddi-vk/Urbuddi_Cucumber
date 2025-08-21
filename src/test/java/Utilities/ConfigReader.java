package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static void loadProperties() {
        try {
            FileInputStream fileInput = new FileInputStream("src/test/java/resources/config.properties");
            properties = new Properties();
            properties.load(fileInput);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }
}
