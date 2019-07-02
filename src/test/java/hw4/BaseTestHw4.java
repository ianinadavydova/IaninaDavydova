package hw4;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import hw3.enums.Page;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.open;
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

    protected User getTestUser() {
        return User.createFromProperties(readProperties("src/test/resources/properties/user.properties"));
    }

    protected HomePage getHomePage() {
        // Step #1 Open test site by URL, test site is opened
        HomePage homePage = open(getUrl(), HomePage.class);

        // Step #2 Assert Browser title
        homePage.getTitle().shouldHave(attribute("text", Page.HOME.getTitle()));

        // Step #3 Perform login
        homePage.login(getTestUser());

        return homePage;
    }

    protected static <PageObjectClass> PageObjectClass createPage(Class<PageObjectClass> pageObjectClass) {
        return WebDriverRunner.getSelenideDriver().page(pageObjectClass);
    }

    @AfterMethod
    public void tearDown() {
        Selenide.close();
    }
}
