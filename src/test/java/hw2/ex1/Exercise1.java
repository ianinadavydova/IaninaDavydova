package hw2.ex1;

import hw2.BaseTestHw2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class Exercise1 extends BaseTestHw2 {

    // TODO It could be made as constant - FIXED
    private final static List<String> EXPECTED_NAV_BAR_ITEMS_TEXTS = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");

    // TODO It could be made as constant - FIXED
    private final static List<String> EXPECTED_IMAGE_TEXTS = Arrays.asList(
            "To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");

    // TODO It could be made as constant - FIXED
    private final static String EXPECTED_JDI_TEXT =
            "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, " +
                    "SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                    "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT "+
                    "ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT "+
                    "ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";

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

        //Step #6-17
        pageContentTest();
    }

    private void pageContentTest() {

        //Step #6 Assert that there are 4 items on the header section are displayed and they have proper texts

        List<WebElement> actualNavBarItems = driver.findElements(By.cssSelector(".nav>li>a"));
        checkElementsAreDisplayed(actualNavBarItems);
        compareLists(actualNavBarItems, EXPECTED_NAV_BAR_ITEMS_TEXTS);

        //Step #7 Assert that there are 4 images on the Index Page...
        List<WebElement> images = driver.findElements(By.cssSelector(".benefit-icon"));
        assertEquals(images.size(), 4);

        //Step #7 ...and they are displayed
        checkElementsAreDisplayed(images);

        //Step #8 Assert that there are 4 texts on the Index Page under icons...
        List<WebElement> actualImageTexts = driver.findElements(By.cssSelector(".benefit-txt"));
        checkElementsAreDisplayed(actualImageTexts);

        //Step #8 ...and they have proper text
        compareLists(actualImageTexts, EXPECTED_IMAGE_TEXTS);

        // Step #9 Assert a text of the main headers
        WebElement actualMainHeaderText = driver.findElement(By.cssSelector("[name='main-title']"));
        checkElementIsDisplayed(actualMainHeaderText);
        softAssert.assertEquals(actualMainHeaderText.getText(), "EPAM FRAMEWORK WISHES…");

        WebElement jdiText = driver.findElement(By.cssSelector("[name='jdi-text']"));
        checkElementIsDisplayed(jdiText);
        softAssert.assertEquals(jdiText.getText(), EXPECTED_JDI_TEXT);

        // Step #10 Assert that there is the iframe in the center of page
        checkElementIsDisplayed(driver.findElement(By.id("iframe")));

        // Step #11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        driver.switchTo().frame("iframe");
        checkElementIsDisplayed(driver.findElement(By.id("epam_logo")));

        // Step #12 Switch to original window back
        driver.switchTo().defaultContent();

        // Step #13 Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.cssSelector("h3 a"));
        checkElementIsDisplayed(subHeader);
        softAssert.assertEquals(subHeader.getText(), "JDI GITHUB");

        // Step #14 Assert that JDI GITHUB is a link and has a proper URL
        softAssert.assertEquals(subHeader.getAttribute("href"), "https://github.com/epam/JDI");

        // Step #15 Assert that there is Left Section
        checkElementIsDisplayed(driver.findElement(By.id("mCSB_1")));

        //Step #16 Assert that there is Footer
        checkElementIsDisplayed(driver.findElement(By.tagName("footer")));

        softAssert.assertAll();
    }
}
