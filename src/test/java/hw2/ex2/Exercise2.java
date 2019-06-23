package hw2.ex2;

import hw2.BaseTestHw2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class Exercise2 extends BaseTestHw2 {

    @Test
    public void exercise2Test() {
        // Step #1 Open test site by URL, test site is opened
        siteOpeningCheck("https://epam.github.io/JDI/index.html");

        // Step #2 Assert Browser title
        browserTitleCheck("Home Page");

        /* Step #3,4 Perform login
            Assert User name in the left-top side of screen that user is loggined */
        loginCheck("epam", "1234", "Piter Chailovskii");

        //Step #5-17
        pageContentActionsTest();

    }

    private void pageContentActionsTest() {
            // Step #5 Click on "Service" subcategory in the header and check that drop down contains options

        List<String> expectedServiceDropdownItems = Arrays.asList(
                "Support", "Dates", "Complex Table", "Simple Table", "Tables With Pages", "Different Elements");

        WebElement headerService = driver.findElement(By.xpath("//ul[@class='uui-navigation nav navbar-nav m-l8']//li//a[contains(.,'Service')]"));
        headerService.click();
        List<WebElement> actualTopBarServiceDropdownItems = driver.findElements(By.cssSelector("ul .dropdown-menu li"));
        compareLists(actualTopBarServiceDropdownItems, expectedServiceDropdownItems);

        //Step #6 Click on Service subcategory in the left section and check that drop down contains options
        WebElement leftService = driver.findElement(By.xpath("//ul[@class='sidebar-menu']//li//a[contains(.,'Service')]"));
        leftService.click();
        List<WebElement> actualSideBarServiceDropdownItems = driver.findElements(By.cssSelector("ul .dropdown-menu li"));
        compareLists(actualSideBarServiceDropdownItems, expectedServiceDropdownItems);

        //Step #7 Open through the header menu Service -> Different Elements Page
        headerService.click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu']//li//a[@href='different-elements.html']")).click();
        assertEquals(driver.getTitle(), "Different Elements");

        // Step #8 Check interface on Different elements page, it contains all needed elements
        List<WebElement> actualCheckBoxesList = driver.findElements(By.className("label-checkbox"));
        List<WebElement> actualRadioButtonsList = driver.findElements(By.className("label-checkbox"));
        List<WebElement> actualButtonsList = driver.findElements(By.cssSelector(".uui-button[value]"));
        WebElement colorsDropDown = driver.findElement(By.cssSelector(".colors .uui-form-element"));

        checkCount(actualCheckBoxesList, 4);
        checkCount(actualRadioButtonsList, 4);
        checkElementIsDisplayed(colorsDropDown);
        checkCount(actualButtonsList, 2);

        //Step #9 Assert that there is Right Section
        checkElementIsDisplayed(driver.findElement(By.name("log-sidebar")));

        //Step #10 Assert that there is Left Section
        checkElementIsDisplayed(driver.findElement(By.name("navigation-sidebar")));

        //Step #11 Select checkboxes
        WebElement waterCheckbox = driver.findElement(By.xpath("//label[contains(.,'Water')]//input"));
        WebElement windCheckbox = driver.findElement(By.xpath("//label[contains(.,'Wind')]//input"));
        selectElement(waterCheckbox);
        selectElement(windCheckbox);

        //Step #12 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.

        List<String> logRecords = getLogRecords();
        assertTrue(logRecords.get(0).endsWith("Wind: condition changed to true"));
        assertTrue(logRecords.get(1).endsWith("Water: condition changed to true"));

        //Step #13 Select radio
        WebElement selenRadioButton = driver.findElement(By.xpath("//label[contains(.,'Selen')]//input"));
        selectElement(selenRadioButton);

        // Step #14 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton.
        assertTrue(getLastLogRecord().contains("metal: value changed to Selen"));

        //Step #15 Select in dropdown
        colorsDropDown.click();
        driver.findElement(By.xpath("//option[contains(.,'Yellow')]")).click();

        //Step #16 Assert that for dropdown there is a log row and value is corresponded to the selected value.
        assertTrue(getLastLogRecord().contains("Colors: value changed to Yellow"));

        // Step #17 Unselect and assert checkboxes

        deselectElement(waterCheckbox);
        deselectElement(windCheckbox);

        // Step #18 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.

        logRecords = getLogRecords();
        assertTrue(logRecords.get(0).endsWith("Wind: condition changed to false"));
        assertTrue(logRecords.get(1).endsWith("Water: condition changed to false"));

        softAssert.assertAll();
    }
}
