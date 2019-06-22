package hw2.ex2;

import hw2.BaseTestHw2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class Exercise2 extends BaseTestHw2 {

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
         Click on "Service" subcategory in the header and check that drop down contains options
         */
        List<String> expectedServiceDropdownItems = Arrays.asList(
                "Support", "Dates", "Complex Table", "Simple Table", "Tables With Pages", "Different Elements");
        driver.findElement(By.xpath("//a[text()[contains(., 'Service')]]")).click();
        List<WebElement> actualServiceDropdownItems = driver.findElements(By.cssSelector("ul .dropdown-menu li"));

        SoftAssert softAssert = new SoftAssert();

         for (int i = 0; i < 4; i++) {
            softAssert.assertEquals(actualServiceDropdownItems.get(i).getText(), expectedServiceDropdownItems.get(i));
        }

        softAssert.assertAll();
    }


}
