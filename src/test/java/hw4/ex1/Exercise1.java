package hw4.ex1;

import hw4.BaseTestHw4;
import hw4.HomePage;
import hw4.User;
import hw4.enums.Page;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.open;

public class Exercise1 extends BaseTestHw4 {
    @Test
    public void Exercise1Test() {
        SoftAssert softAssert = new SoftAssert();

        // Step #1 Open test site by URL, test site is opened
        HomePage homePage = open(getUrl(), HomePage.class);

        // Step #2 Assert Browser title
        homePage.getTitle().shouldHave(attribute("text", Page.HOME.getTitle()));

        // Step #3 Perform login
        User testUser = new User(getUserProperties());
        homePage.login(testUser);

        // Step #4 Assert User name in the left-top side of screen that user is loggined

    }
}
