package hw3;

import hw3.utils.FileUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTestHw3 {

    protected WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeSuite
    public void setUpDriverPath() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        Properties url = FileUtils.readPropertiesFromFile("src/main/resources/url.properties");
        driver.get(url.getProperty("url.link"));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void initSoftAssert() {
        softAssert = new SoftAssert();
    }

    @AfterClass
    public void teatDown() {
        driver.close();
    }

/*    protected void siteOpeningCheck(String url) {
        assertEquals(driver.getCurrentUrl(), url);
    }

    protected void browserTitleCheck(String browserTitle) {
        assertEquals(driver.getTitle(), browserTitle);
    }

    protected void loginCheck() {
        assertEquals(driver.findElement(By.id("user-name")).getText(), userName.toUpperCase());
    }

    protected void checkElementIsDisplayed(WebElement webElement) {
        softAssert.assertTrue(webElement.isDisplayed());
    }

    protected void checkElementsAreDisplayed(List<WebElement> webElements) {
        for (WebElement element : webElements) {
            checkElementIsDisplayed(element);
        }
    }

    protected void compareLists(List<WebElement> actual, List<String> expected) {
        assertEquals(actual.size(), expected.size());
        // TODO It is better to use i < actual.size() - FIXED
        for (int i = 0; i < actual.size(); ++i) {
            softAssert.assertEquals(actual.get(i).getText(), expected.get(i));
        }
    }

    protected void checkCount(List<WebElement> actual, int expectedCount) {
        assertEquals(actual.size(), expectedCount);
    }

    protected void selectElement(WebElement element) {
        element.click();
        assertTrue(element.isSelected());
    }

    protected void deselectElement(WebElement element) {
        element.click();
        assertFalse(element.isSelected());
    }

    protected String getLastLogRecord() {
        return driver.findElement(By.cssSelector(".panel-body-list.logs > li")).getText();
    }*/
}
