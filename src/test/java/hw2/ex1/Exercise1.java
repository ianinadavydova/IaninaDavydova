package hw2.ex1;

import hw2.BaseTestHw2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

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

        /*Step #6
         Assert that there are 4 items on the header section are displayed
         and they have proper texts
         */
        pageContentTest();
    }

    private void pageContentTest() {

        //Step #6 Assert that there are 4 items on the header section are displayed...

        List<String> expectedNavBarItemsTexts = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        List<WebElement> actualNavBarItems = driver.findElements(By.cssSelector(".nav>li>a"));
        assertEquals(actualNavBarItems.size(), 4);

        //Step #6 ...and they have proper texts
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < 4; i++) {
            softAssert.assertEquals(actualNavBarItems.get(i).getText(), expectedNavBarItemsTexts.get(i));
        }

        //Step #7 Assert that there are 4 images on the Index Page...
        List<WebElement> images = driver.findElements(By.cssSelector(".benefit-icon"));
        assertEquals(images.size(), 4);

        //Step #7 ...and they are displayed
        for (int i = 0; i < 4; i++) {
            softAssert.assertTrue(images.get(i).isDisplayed());
        }

        //Step #8 Assert that there are 4 texts on the Index Page under icons...
        List<WebElement> actualImageTexts = driver.findElements(By.cssSelector(".benefit-txt"));
        assertEquals(actualImageTexts.size(), 4);

        //Step #8 ...and they have proper text
        List<String> expectedImageTexts = Arrays.asList(
                "To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");

        for (int i = 0; i < 4; i++) {
            softAssert.assertTrue(actualImageTexts.get(i).isDisplayed());
            softAssert.assertEquals(actualImageTexts.get(i).getText(), expectedImageTexts.get(i));
        }

        // Step #9 Assert a text of the main headers
        List<String> expectedMainHeadersTexts = Arrays.asList(
                "EPAM framework Wishes…",
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod " +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud " +
                        "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat Duis aute irure dolor in " +
                        "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
        );

        List<WebElement> actualMainHeadersTexts = driver.findElements(By.cssSelector("[name='main-title']"));
        actualMainHeadersTexts.add(driver.findElement(By.cssSelector("[name='jdi-text']")));
        for (int i = 0; i < 2; i++) {
            checkElementIsDisplayed(actualMainHeadersTexts.get(i));
            softAssert.assertEquals(actualMainHeadersTexts.get(i).getText(), expectedMainHeadersTexts.get(i).toUpperCase());
        }

        // Step #10 Assert that there is the iframe in the center of page
        checkElementIsDisplayed(driver.findElement(By.id("iframe")));

        // Step #11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        driver.switchTo().frame("iframe");
        checkElementIsDisplayed(driver.findElement(By.id("epam_logo")));

        // Step #12 Switch to original window back
        driver.switchTo().defaultContent();

        // Step #13 Assert a text of the sub header
        checkElementIsDisplayed(driver.findElement(By.cssSelector("h3 a")));
        assertEquals(driver.findElement(By.cssSelector("h3 a")).getText(), "JDI GITHUB");

        // Step #14 Assert that JDI GITHUB is a link and has a proper URL
        checkElementIsDisplayed(driver.findElement(By.cssSelector("h3 a")));
        assertEquals(driver.findElement(By.cssSelector("h3 a")).getAttribute("href"), "https://github.com/epam/JDI");

        // Step #15 Assert that there is Left Section
        checkElementIsDisplayed(driver.findElement(By.id("mCSB_1")));

        //Assert that there is Footer
        checkElementIsDisplayed(driver.findElement(By.tagName("footer")));

        softAssert.assertAll();
    }

    private void checkElementIsDisplayed(WebElement webElement) {
        assertTrue(webElement.isDisplayed());
    }

}
