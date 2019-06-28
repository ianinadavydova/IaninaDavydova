package hw3.steps;

import hw3.enums.Header;
import hw3.enums.ImageLabel;
import hw3.enums.NavBarMenu;
import hw3.enums.SubHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Exercise1Steps extends BaseSteps {

    public Exercise1Steps(WebDriver driver, String url, SoftAssert softAssert) {
        super(driver, url, softAssert);
    }

    public void checkNavBarItems() {
        List<WebElement> navBarItems = homePage.getNavBarItems();
        checkAllElementsAreDisplayed(navBarItems);
        compareLists(navBarItems, NavBarMenu.values());
    }

    public void checkImages() {
        List<WebElement> images = homePage.getImages();
        softAssert.assertEquals(images.size(), ImageLabel.values().length);
        checkAllElementsAreDisplayed(images);
    }

    public void checkImageTexts() {
        List<WebElement> imageTexts = homePage.getImageTexts();
        checkAllElementsAreDisplayed(imageTexts);
        compareLists(imageTexts, ImageLabel.values());
    }

    public void checkHeaders() {
        WebElement mainHeader = homePage.getMainHeader();
        checkElementIsDisplayed(mainHeader);
        softAssert.assertEquals(mainHeader.getText(), Header.MAIN.text);

        WebElement jdiHeader = homePage.getJdiText();
        checkElementIsDisplayed(jdiHeader);
        softAssert.assertEquals(jdiHeader.getText(), Header.JDI.text);
    }

    public void checkIFrame() {
        checkElementIsDisplayed(homePage.getIFrame());
    }

    public void checkEpamLogo() {
        checkElementIsDisplayed(homePage.getEpamLogo());
    }

    public void checkSubHeaderText() {
        WebElement subHeader = homePage.getSubHeader();
        checkElementIsDisplayed(subHeader);
        softAssert.assertEquals(subHeader.getText(), SubHeader.TEXT.value);
    }

    public void checkSubHeaderLink() {
        WebElement subHeader = homePage.getSubHeader();
        softAssert.assertEquals(subHeader.getAttribute("href"), SubHeader.LINK.value);
    }

    public void checkLeftSection() {
        checkElementIsDisplayed(homePage.getLeftSection());
    }

    public void checkFooter() {
        checkElementIsDisplayed(homePage.getFooter());
    }
}
