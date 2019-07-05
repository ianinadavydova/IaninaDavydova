package hw3.steps;

import hw3.enums.Header;
import hw3.enums.ImageLabel;
import hw3.enums.NavBarMenu;
import hw3.enums.SubHeader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Exercise1Steps extends BaseSteps {

    public Exercise1Steps(WebDriver driver, String url, SoftAssert softAssert) {
        super(driver, url, softAssert);
    }

    @Step("Check navigation bar items")
    public void checkNavBarItems() {
        List<WebElement> navBarItems = homePage.getNavBarItems();
        checkAllElementsAreDisplayed(navBarItems);
        compareLists(navBarItems, NavBarMenu.values());
    }

    @Step("Check images are displayed")
    public void checkImages() {
        List<WebElement> images = homePage.getImages();
        softAssert.assertEquals(images.size(), ImageLabel.values().length);
        checkAllElementsAreDisplayed(images);
    }

    @Step("Check image text are displayed and correct")
    public void checkImageTexts() {
        List<WebElement> imageTexts = homePage.getImageTexts();
        checkAllElementsAreDisplayed(imageTexts);
        compareLists(imageTexts, ImageLabel.values());
    }

    @Step("Check header are displayed and correct")
    public void checkHeaders() {
        WebElement mainHeader = homePage.getMainHeader();
        checkElementIsDisplayed(mainHeader);
        softAssert.assertEquals(mainHeader.getText(), Header.MAIN.text);

        WebElement jdiHeader = homePage.getJdiText();
        checkElementIsDisplayed(jdiHeader);
        softAssert.assertEquals(jdiHeader.getText(), Header.JDI.text);
    }

    @Step("Check iFrame exists")
    public void checkIFrame() {
        checkElementIsDisplayed(homePage.getIFrame());
    }

    @Step("Check Epam logo in iFrame")
    public void checkEpamLogo() {
        checkElementIsDisplayed(homePage.getEpamLogo());
    }

    @Step("Check subheaders texts")
    public void checkSubHeaderText() {
        WebElement subHeader = homePage.getSubHeader();
        checkElementIsDisplayed(subHeader);
        softAssert.assertEquals(subHeader.getText(), SubHeader.TEXT.value);
    }

    @Step("Check subheaders links are correct")
    public void checkSubHeaderLink() {
        WebElement subHeader = homePage.getSubHeader();
        softAssert.assertEquals(subHeader.getAttribute("href"), SubHeader.LINK.value);
    }

    @Step("Check left section exists")
    public void checkLeftSection() {
        checkElementIsDisplayed(homePage.getLeftSection());
    }

    @Step("Check footer exists")
    public void checkFooter() {
        checkElementIsDisplayed(homePage.getFooter());
    }
}
