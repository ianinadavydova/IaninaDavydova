package hw7hw8;

import com.epam.jdi.light.driver.get.DriverData;
import hw3.enums.NavBarMenu;
import hw7hw8.entities.MetalsColorsEntity;
import hw7hw8.entities.User;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.ui.html.PageFactory.initElements;
import static hw7hw8.HomePage.*;
import static hw7hw8.JdiTestSite.homePage;
import static hw7hw8.JdiTestSite.metalsColorsPage;
import static hw7hw8.MetalsColorsPage.metalsColorsForm;

public class MetalColorsFormTest {
    // TODO
    private static final User PITER = new User().set(user -> {
        user.userName = "epam";
        user.password = "1234";
        user.fullName = "PITER CHAILOVSKII";
    });

    @BeforeClass(alwaysRun = true)
    public static void setUp() {
        DriverData.CHROME_OPTIONS = () -> {
            ChromeOptions cap = new ChromeOptions();
            cap.addArguments("--start-maximized");
            return cap;
        };
        initElements(JdiTestSite.class);

        // TODO
        homePage.open();
        homePage.checkOpened();
        userIcon.click();
        loginForm.loginAs(PITER);
    }

    // TODO
    @Test(dataProviderClass = DataProvider.class, dataProvider = "staticDataSet")
    public void testStaticData(MetalsColorsEntity formData, List<String> expectedLog) {
        testImpl(formData, expectedLog);
    }

    @Test(dataProviderClass = DataProvider.class, dataProvider = "dynamicDataSet")
    public void testDynamicData(MetalsColorsEntity formData, List<String> expectedLog) {
        testImpl(formData, expectedLog);
    }
    // !TODO

    public void testImpl(MetalsColorsEntity formData, List<String> expectedLog) {
        homePage.checkLoggedin(PITER);
        header.select(NavBarMenu.METALS_COLORS);
        metalsColorsPage.checkOpened();
        metalsColorsForm.submit(formData);
        // TODO
        Assert.assertTrue(metalsColorsPage.getLog().containsAll(expectedLog));
    }

    @AfterClass
    public void afterClass() {
        killAllSeleniumDrivers();
    }
}
