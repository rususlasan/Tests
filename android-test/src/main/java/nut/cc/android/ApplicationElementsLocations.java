package nut.cc.android;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * метод getInstance() возвращает файл свойств, хранящий информации о локации эллементов в приложении
 */
@Component
public class ApplicationElementsLocations {

    private static Properties appElementsLocation;

    private ApplicationElementsLocations() {}

    public static Properties getInstance() {
        if (appElementsLocation == null) loadProperties();

        return appElementsLocation;

    }

    private static void loadProperties() {
        appElementsLocation = new Properties();
        InputStream is;
        try {
            is = new FileInputStream("./src/main/resources/android.properties");
            appElementsLocation.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
