package hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

public class BaseTestHw2 {

    protected WebDriver driver;

    @BeforeSuite
    public void setUpDriverPath() {
        System.setProperty("webdriver.chrome.driver",
                Paths.get("src/test/resources/driver/chromedriver.exe").toAbsolutePath().toString());
    }

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://epam.github.io/JDI/");
        driver.manage().window().maximize();
    }

    @AfterTest
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
}
