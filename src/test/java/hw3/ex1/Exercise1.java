package hw3.ex1;

import hw3.BaseTestHw3;
import hw3.steps.Exercise1Steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise1 extends BaseTestHw3 {

    Exercise1Steps exercise1Steps = new Exercise1Steps(driver);

    @Test
    public void exercise1Test() {
        // Step #1 Open test site by URL, test site is opened
        exercise1Steps.checkURL();

        // Step #2 Assert Browser title
        exercise1Steps.checkPageTitle();

        //Step #3 Perform login
        exercise1Steps.login();

        // Step#4 Assert User name in the left-top side of screen that user is loggined
        exercise1Steps.checkUser();

        // Step #5 Assert Browser title
        exercise1Steps.checkPageTitle();

        //Step #6 Assert that there are 4 items on the header section are displayed and they have proper texts
        exercise1Steps.checkNavBarItems();

     /*   //Step #7 Assert that there are 4 images on the Index Page...
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

        softAssert.assertAll();*/
    }
}
