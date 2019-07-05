package hw3.steps;

import hw3.enums.Page;
import hw3.voids.HomePage;
import hw3.utils.FileUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public abstract class BaseSteps {
    protected final HomePage homePage;
    private final String url;
    private Properties userProperties;
    protected final SoftAssert softAssert;

    protected BaseSteps(WebDriver driver, String url, SoftAssert softAssert) {
        homePage = new HomePage(driver);
        this.url = url;
        this.softAssert = softAssert;
        // TODO It is better to have try-catch block on the low level - FIXED
        try {
            userProperties = FileUtils.readPropertiesFromFile("src/test/resources/properties/user.properties");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Step("Check current url")
    public void checkURL() {
        softAssert.assertEquals(homePage.getURL(), url);
    }

    @Step("Check current page title")
    public void checkPageTitle() {
        softAssert.assertEquals(homePage.getTitle(), Page.HOME.getTitle());
    }

    @Step("Perform login")
    public void login() {
        homePage.login(userProperties.getProperty("user.name"), userProperties.getProperty("user.password"));
    }

    @Step("Check loginned user")
    public void checkUser() {
        softAssert.assertEquals(homePage.getUserName(), userProperties.getProperty("user.user.name"));
    }

    @Step("Check user and fail")
    public void checkUserFail() {
        softAssert.assertEquals(homePage.getUserName(), "PITER CHAILOVSKII1");
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
