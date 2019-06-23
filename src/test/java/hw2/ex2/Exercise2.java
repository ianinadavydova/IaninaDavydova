package hw2.ex2;

import hw2.BaseTestHw2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Exercise2 extends BaseTestHw2 {

    // TODO It could be made as constant - FIXED
    private static final List<String> EXPECTED_SERVICE_DROPDOWN_ITEMS = Arrays.asList(
            "Support", "Dates", "Complex Table", "Simple Table", "Tables With Pages", "Different Elements");

    private static final String WATER_CHECKBOX = "Water";
    private static final String WIND_CHECKBOX = "Wind";

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

        WebElement headerService = driver.findElement(By.xpath("//ul[@class='uui-navigation nav navbar-nav m-l8']//li//a[contains(.,'Service')]"));
        headerService.click();
        List<WebElement> actualTopBarServiceDropdownItems = driver.findElements(By.cssSelector("ul .dropdown-menu li"));
        compareLists(actualTopBarServiceDropdownItems, EXPECTED_SERVICE_DROPDOWN_ITEMS);

        //Step #6 Click on Service subcategory in the left section and check that drop down contains options
        WebElement leftService = driver.findElement(By.xpath("//ul[@class='sidebar-menu']//li//a[contains(.,'Service')]"));
        leftService.click();
        List<WebElement> actualSideBarServiceDropdownItems = driver.findElements(By.cssSelector("ul .dropdown-menu li"));
        compareLists(actualSideBarServiceDropdownItems, EXPECTED_SERVICE_DROPDOWN_ITEMS);

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
        //Step #12 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        // TODO Is it possible make name of the checkbox, radiobutton as input parameter? - FIXED
        // TODO Name should be not part of the locator in the test - FIXED
        WebElement waterCheckbox = selectCheckboxAndCheckLog(WATER_CHECKBOX);
        WebElement windCheckbox = selectCheckboxAndCheckLog(WIND_CHECKBOX);

        //Step #13 Select radio
        findAndSelectElementByLabel("Selen");
        // Step #14 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton.
        assertTrue(getLastLogRecord().contains("metal: value changed to Selen"));

        //Step #15 Select in dropdown
        // TODO Why do you not use Select element for the Dropdown? - FIXED
        colorsDropDown.click();
        Select dropDownColor = new Select(colorsDropDown);
        dropDownColor.selectByVisibleText("Yellow");

        //Step #16 Assert that for dropdown there is a log row and value is corresponded to the selected value.
        assertTrue(getLastLogRecord().contains("Colors: value changed to Yellow"));

        // Step #17 Unselect and assert checkboxes
        // Step #18 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        deselectCheckboxAndCheckLog(waterCheckbox, WATER_CHECKBOX);
        deselectCheckboxAndCheckLog(windCheckbox, WIND_CHECKBOX);

        softAssert.assertAll();
    }

    private WebElement findAndSelectElementByLabel(String name) {
        WebElement result = driver.findElement(By.xpath(String.format("//label[contains(.,'%s')]//input", name)));
        selectElement(result);
        return result;
    }

    private WebElement selectCheckboxAndCheckLog(String name) {
        WebElement checkbox = findAndSelectElementByLabel(name);
        softAssert.assertTrue(getLastLogRecord().endsWith(name + ": condition changed to true"));
        return checkbox;
    }

    private void deselectCheckboxAndCheckLog(WebElement checkbox, String name) {
        deselectElement(checkbox);
        softAssert.assertTrue(getLastLogRecord().endsWith(name + ": condition changed to false"));
    }
}
