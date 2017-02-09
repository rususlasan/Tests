package nut.cc.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

@Component
public class DriverProducer {

    public static AppiumDriver getInstance() {
        AppiumDriver driver = null;
        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            DesiredCapabilities capabilities = new DesiredCapabilities();

            setAllCapabilities(capabilities);

            driver = new AndroidDriver(url, capabilities);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static void setAllCapabilities(DesiredCapabilities capabilities) {
        Properties properties = new Properties();
        InputStream is;
        try {
            is = new FileInputStream("./src/main/resources/capability.properties");
            properties.load(is);
        } catch (IOException e) {
            System.out.println("***** Could not load property!!!");
            e.printStackTrace();
        }
        if (properties.size() == 0) return;

        capabilities.setCapability("platformName", properties.getProperty("platform.Name"));
        capabilities.setCapability("deviceName", properties.getProperty("device.Name"));
        capabilities.setCapability("appPackage", properties.getProperty("app.Package"));
        capabilities.setCapability("appActivity", properties.getProperty("app.Activity"));
    }
}
