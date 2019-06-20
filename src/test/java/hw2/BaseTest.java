package hw2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

public class BaseTest {

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
    }

    @BeforeTest
    public void driverManageTest() {
        driver.manage().window().maximize();
    }

    @AfterTest
    public void teatDown() {
        driver.close();
    }
}
