package hw4;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Properties;

import static hw3.utils.FileUtils.readPropertiesFromFile;

public class BaseTestHw4 {

    @BeforeMethod
    public void setUp() {
        Configuration.browser = Browsers.CHROME;
        Configuration.pollingInterval = 500;
        Configuration.timeout = 5000;
        Configuration.reportsFolder = "target/selenide/reports.test";
        Configuration.startMaximized = true;
    }

    private Properties readProperties(String filePath) {
        Properties properties = null;
        try {
            properties = readPropertiesFromFile(filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
        return properties;
    }

    protected String getUrl() {
        String url = null;
        url = readProperties("src/test/resources/properties/url.properties").getProperty("url.link");
        return url;
    }

    protected Properties getUserProperties() {
        Properties userProperties = null;
        userProperties = readProperties("src/test/resources/properties/user.properties");
        return userProperties;
    }

    @AfterMethod
    public void tearDown() {
        Selenide.close();
    }
}
