package hw2.ex1;

import hw2.BaseTestHw2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class Exercise1 extends BaseTestHw2 {
    @Test
    public void exercise1Test() {
        // Step #1 Open test site by URL, test site is opened
        siteOpeningCheck("https://epam.github.io/JDI/index.html");

        // Step #2 Assert Browser title
        browserTitleCheck("Home Page");

        /* Step #3,4 Perform login
            Assert User name in the left-top side of screen that user is loggined */
        loginCheck("epam", "1234", "Piter Chailovskii");

        // Step #5 Assert Browser title
        browserTitleCheck("Home Page");

        //Step #6 Assert that there are 4 items on the header section are displayed and they have proper texts
        pageContentTest();


    }

    private void pageContentTest() {

        List<String> expectedNavBarItemsTexts = Arrays.asList("HOME", "CONTACT FORMd", "SERVICE", "METALS & COLORS");
        List<WebElement> actualNavBarItems = driver.findElements(By.xpath("//ul[@class='uui-navigation nav navbar-nav m-l8']/li"));
        assertEquals(actualNavBarItems.size(), 4);

        List<String> actualNavBarItemsTexts = actualNavBarItems.stream().map(WebElement::getText).collect(Collectors.toList());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualNavBarItemsTexts, expectedNavBarItemsTexts);
    }

}
