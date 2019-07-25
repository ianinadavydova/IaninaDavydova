package hw7;

import com.epam.jdi.light.driver.get.DriverData;
import hw3.enums.NavBarMenu;
import hw7hw8.JdiTestSite;
import hw7hw8.entities.MetalsColors;
import hw7hw8.entities.User;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.ui.html.PageFactory.initElements;
import static hw7hw8.JdiTestSite.homePage;
import static hw7hw8.JdiTestSite.metalsColorsPage;
import static hw7hw8.pages.HomePage.*;
import static hw7hw8.pages.MetalsColorsPage.metalsColorsForm;

public class MetalsColorsFormTest {

    @BeforeClass(alwaysRun = true)
    public static void setUp() {
        DriverData.CHROME_OPTIONS = () -> {
            ChromeOptions cap = new ChromeOptions();
            cap.addArguments("--start-maximized");
            return cap;
        };
        initElements(JdiTestSite.class);
    }

    // TODO - FIXED test structure
    @Test(dataProviderClass = DataProvider.class, dataProvider = "dataSet")
    public void test(User user, MetalsColors formData, List<String> expectedLog) {
        homePage.open();
        homePage.checkOpened();
        userIcon.click();
        loginForm.loginAs(user);
        homePage.checkLoggedin(user);
        header.select(NavBarMenu.METALS_COLORS);
        metalsColorsPage.checkOpened();
        metalsColorsForm.submit(formData);
        // TODO - FIXED assertion
        Assert.assertEquals(metalsColorsPage.getLog(), expectedLog);
    }

    @AfterMethod
    public void logout() {
        homePage.logout();
    }

    @AfterClass
    public void afterClass() {
        killAllSeleniumDrivers();
    }
}
