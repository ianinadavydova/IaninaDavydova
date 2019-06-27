package hw3.steps;

import hw3.enums.BaseEnum;
import hw3.enums.NavBarMenu;
import hw3.voids.HomePage;
import hw3.utils.FileUtils;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public abstract class BaseSteps {
    protected HomePage homePage;


    Properties userProperties = FileUtils.readPropertiesFromFile("src/main/resources/user.properties");
    Properties urlProperties = FileUtils.readPropertiesFromFile("src/main/resources/url.properties");

    public void checkURL() {
        assertEquals(homePage.getURL(), urlProperties.getProperty("url.link"));
    }

    public void checkPageTitle() {
        assertEquals(homePage.getTitle(), BaseEnum.HOME_PAGE_TITLE.getPageTitle());
    }

    public void login() {
        homePage.login(userProperties.getProperty("user.name"), userProperties.getProperty("user.password"));
    }

    public void checkUser() {
        assertEquals(homePage.getUserName(), userProperties.getProperty("user.user.name"));
    }

    public void checkNavBarItems() {
        List<WebElement> navBarItems = homePage.getNavBarItems();
        checkAllElementsAreDisplayed(navBarItems);
        compareLists(navBarItems, NavBarMenu.values());
    }

    private void checkAllElementsAreDisplayed(List<WebElement> elements) {
        for (WebElement element : elements) {
            assertTrue(element.isDisplayed());
        }
    }

    private <Item> void compareLists(List<WebElement> actual, Item[] expected) {
        assertEquals(actual.size(), expected.length);
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(actual.get(i).getText(), expected[i].toString());
        }
    }
}
