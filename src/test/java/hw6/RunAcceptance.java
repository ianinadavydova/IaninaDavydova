package hw6;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.close;

@CucumberOptions(
        features = { "classpath:hw6" },
        glue = "classpath:hw6"
)
public class RunAcceptance extends AbstractTestNGCucumberTests {
    @BeforeSuite
    public void setUp() {
        Configuration.browser = Browsers.CHROME;
        Configuration.pollingInterval = 500;
        Configuration.timeout = 5000;
        Configuration.reportsFolder = "target/selenide/reports.test";
        Configuration.startMaximized = true;
    }

    @AfterSuite
    public void tearDown() {
        close();
    }
}