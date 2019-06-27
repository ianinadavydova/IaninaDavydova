package hw3.ex2;

import hw3.BaseTestHw3;
import hw3.enums.CheckBox;
import hw3.enums.Color;
import hw3.enums.RadioButton;
import hw3.steps.Exercise2Steps;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Exercise2 extends BaseTestHw3 {

    @Test
    public void exercise2Test() {
        SoftAssert softAssert = new SoftAssert();
        Exercise2Steps exercise2Steps = null;
        try {
            exercise2Steps = new Exercise2Steps(driver, url, softAssert);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }

        exercise2Steps.checkURL();

        // Step #2 Assert Browser title
        exercise2Steps.checkPageTitle();

        //Step #3 Perform login
        exercise2Steps.login();

        // Step#4 Assert User name in the left-top side of screen that user is loggined
        exercise2Steps.checkUser();

        // Step #5 Click on "Service" subcategory in the header and check that drop down contains options
        exercise2Steps.checkService();

        //Step #6 Click on Service subcategory in the left section and check that drop down contains options
        exercise2Steps.checkLeftService();

        //Step #7 Open through the header menu Service -> Different Elements Page
        exercise2Steps.openDifferentElementsPage();

        // Step #8 Check interface on Different elements page, it contains all needed elements
        exercise2Steps.checkDifferentElementsCount();

        //Step #9 Assert that there is Right Section
        exercise2Steps.checkLogSideBar();

        //Step #10 Assert that there is Left Section
        exercise2Steps.checkNavigationSideBar();

        //Step #11 Select checkboxes
        //Step #12 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        exercise2Steps.selectCheckBoxAndCheckLog(CheckBox.WATER);
        exercise2Steps.selectCheckBoxAndCheckLog(CheckBox.WIND);

        //Step #13 Select radio
        //Step #14 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton.
        exercise2Steps.selectRadioButtonAndCheckLog(RadioButton.SELEN);

        //Step #15 Select in dropdown
        //Step #16 Assert that for dropdown there is a log row and value is corresponded to the selected value.
        exercise2Steps.selectColorsDropDownAndCheckLog(Color.YELLOW);

        // Step #17 Unselect and assert checkboxes
        // Step #18 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        exercise2Steps.deselectCheckBoxAndCheckLog(CheckBox.WATER);
        exercise2Steps.deselectCheckBoxAndCheckLog(CheckBox.WIND);

        softAssert.assertAll();
    }
}
