package hw3.steps;

import hw3.enums.Page;
import hw3.voids.HomePage;
import hw3.utils.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public abstract class BaseSteps {
    protected final HomePage homePage;
    private final String url;
    private final Properties userProperties;
    protected final SoftAssert softAssert;

    protected BaseSteps(WebDriver driver, String url, SoftAssert softAssert) throws IOException {
        homePage = new HomePage(driver);
        this.url = url;
        this.softAssert = softAssert;
        userProperties = FileUtils.readPropertiesFromFile("src/test/resources/properties/user.properties");
    }

    public void checkURL() {
        softAssert.assertEquals(homePage.getURL(), url);
    }

    public void checkPageTitle() {
        softAssert.assertEquals(homePage.getTitle(), Page.HOME.getTitle());
    }

    public void login() {
        homePage.login(userProperties.getProperty("user.name"), userProperties.getProperty("user.password"));
    }

    public void checkUser() {
        softAssert.assertEquals(homePage.getUserName(), userProperties.getProperty("user.user.name"));
    }

    protected void checkElementIsDisplayed(WebElement element) {
        softAssert.assertTrue(element.isDisplayed());
    }

    protected void checkAllElementsAreDisplayed(List<WebElement> elements) {
        for (WebElement element : elements) {
            softAssert.assertTrue(element.isDisplayed());
        }
    }

    protected  <Item> void compareLists(List<WebElement> actual, Item[] expected) {
        assertEquals(actual.size(), expected.length);
        for (int i = 0; i < expected.length; ++i) {
            softAssert.assertEquals(actual.get(i).getText(), expected[i].toString());
        }
    }
}
