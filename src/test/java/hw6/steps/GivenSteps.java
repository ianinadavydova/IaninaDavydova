package hw6.steps;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java.en.Given;
import hw3.voids.DifferentElementsPage;
import hw4.HomePage;
import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static hw3.utils.FileUtils.readPropertiesFromFile;
import static org.testng.Assert.assertNotNull;

public class GivenSteps extends StepBackground {
    @Given("^I am on the JDI Home Page$")
    public void openEpamJDIPage() {
        if (homePage == null)
            homePage = open(getUrl(), HomePage.class);
    }

    @Given("^I am on the 'Different Elements' page$")
    public void openDifferentElementsPage() {
        if (differentElementsPage == null) {
            assertNotNull(homePage);
            homePage.getService().click();
            homePage.getDifferentElementsItem().click();
            differentElementsPage = new DifferentElementsPage(WebDriverRunner.getWebDriver());
        }
    }

    private String getUrl() {
        return readProperties("src/test/resources/properties/url.properties").getProperty("url.link");
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
}
