package hw6.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import hw3.voids.DifferentElementsPage;
import hw4.HomePage;
import hw4.User;
import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static hw3.utils.FileUtils.readPropertiesFromFile;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class GivenSteps extends StepBackground {
    @Given("^I am on the JDI Home Page$")
    public void openEpamJDIPage() {
        assertNull(homePage);
        homePage = open(getUrl(), HomePage.class);
    }

    @After
    public void clearPages() {
        homePage = null;
        differentElementsPage = null;
    }

    @Before("@NeedRelogin")
    public void logout() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        Selenide.refresh();
    }

    @Given("^I login as user \"([^\"]*)\"$")
    public void loginAsUser(String expectedName) {
        homePage.login(User.createFromProperties(readProperties("src/test/resources/properties/user.properties")));
        AssertionsSteps.checkUsername(homePage, expectedName);
    }

    @Given("^I am on the 'Different Elements' page$")
    public void openDifferentElementsPage() {
        assertNull(differentElementsPage);
        assertNotNull(homePage);
        homePage.getService().click();
        homePage.getDifferentElementsItem().click();
        differentElementsPage = new DifferentElementsPage(WebDriverRunner.getWebDriver());
    }

    private String url = null;

    private String getUrl() {
        if (url == null)
            url = readProperties("src/test/resources/properties/url.properties").getProperty("url.link");
        return url;
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
