package hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class BaseTestHw2 {

    protected WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeSuite
    public void setUpDriverPath() {
        System.setProperty("webdriver.chrome.driver",
                Paths.get("src/test/resources/driver/chromedriver.exe").toAbsolutePath().toString());
    }

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://epam.github.io/JDI/");
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

    protected void siteOpeningCheck(String url) {
        assertEquals(driver.getCurrentUrl(), url);
    }

    protected void browserTitleCheck(String browserTitle) {
        assertEquals(driver.getTitle(), browserTitle);
    }

    protected void loginCheck(String login, String password, String userName) {
        driver.findElement(By.id("user-icon")).click();
        driver.findElement(By.id("name")).sendKeys(login);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='login-button']")).click();
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
        for (int i = 0; i != actual.size(); ++i) {
            softAssert.assertEquals(actual.get(i).getText(), expected.get(i));
        }
    }

    protected void checkCount(List<WebElement> actual, int expectedCount) {
        assertEquals(actual.size(), expectedCount);
    }

    protected void selectElement(WebElement element) {
        assertFalse(element.isSelected());
        element.click();
        assertTrue(element.isSelected());
    }

    protected void deselectElement(WebElement element) {
        assertTrue(element.isSelected());
        element.click();
        assertFalse(element.isSelected());
    }

    protected List<String> getLogRecords() {
        List<WebElement> logRecords = driver.findElements(By.cssSelector(".panel-body-list.logs > li"));
        return logRecords.stream().map(logRecord -> logRecord.getText()).collect(Collectors.toList());
    }

    protected String getLastLogRecord() {
        return driver.findElement(By.cssSelector(".panel-body-list.logs > li")).getText();
    }
}
