package demo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties loadProperties(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
