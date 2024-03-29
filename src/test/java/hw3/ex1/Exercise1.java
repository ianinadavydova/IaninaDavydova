package hw3.ex1;

import hw3.BaseTestHw3;
import hw3.steps.Exercise1Steps;
import hw5.ex2.AllureAttachmentListener;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(AllureAttachmentListener.class)
public class Exercise1 extends BaseTestHw3 {

    @Feature("Tests hw3 exercise1")
    @Story("JDI page")
    @Description("Testing JDI page content")
    @Test
    public void exercise1Test() {
        SoftAssert softAssert = new SoftAssert();
        Exercise1Steps exercise1Steps = new Exercise1Steps(driver, url, softAssert);

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

        //Step #7 Assert that there are 4 images on the Index Page and they are displayed
        exercise1Steps.checkImages();

        //Step #8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        exercise1Steps.checkImageTexts();

        // Step #9 Assert a text of the main headers
        exercise1Steps.checkHeaders();

        // Step #10 Assert that there is the iframe in the center of page
        exercise1Steps.checkIFrame();

        // Step #11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        // Step #12 Switch to original window back
        exercise1Steps.checkEpamLogo();

        // Step #13 Assert a text of the sub header
        exercise1Steps.checkSubHeaderText();

        // Step #14 Assert that JDI GITHUB is a link and has a proper URL
        exercise1Steps.checkSubHeaderLink();

        // Step #15 Assert that there is Left Section
        exercise1Steps.checkLeftSection();

        //Step #16 Assert that there is Footer
        exercise1Steps.checkFooter();

        softAssert.assertAll();
    }
}
