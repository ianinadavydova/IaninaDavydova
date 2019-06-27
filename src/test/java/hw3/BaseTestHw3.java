package hw3;

import hw3.utils.FileUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTestHw3 {

    protected WebDriver driver;
    protected String url;

    @BeforeSuite
    public void setUpDriverPath() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setUp() throws IOException {
        driver = new ChromeDriver();
        url = FileUtils.readPropertiesFromFile("src/test/resources/properties/url.properties").getProperty("url.link");
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
