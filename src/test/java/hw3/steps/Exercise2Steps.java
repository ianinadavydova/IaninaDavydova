package hw3.steps;

import hw3.enums.*;
import hw3.voids.DifferentElementsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Exercise2Steps extends BaseSteps {

    private final DifferentElementsPage differentElementsPage;

    public Exercise2Steps(WebDriver driver, String url, SoftAssert softAssert) {
        super(driver, url, softAssert);
        differentElementsPage = new DifferentElementsPage(driver);
    }

    @Step("Check Service dropdown items")
    public void checkService() {
        homePage.getService().click();
        checkAreValidServiceDropDownItems(homePage.getServiceDropdownItems());
    }

    @Step("Check left Service dropdown items")
    public void checkLeftService() {
        homePage.getLeftService().click();
        checkAreValidServiceDropDownItems(homePage.getLeftServiceDropdownItems());
    }

    @Step("Open Different Elements page")
    public void openDifferentElementsPage() {
        homePage.getService().click();
        homePage.getDifferentElementsItem().click();
        softAssert.assertEquals(differentElementsPage.getTitle(), Page.DIFFERENT_ELEMENTS.getTitle());
    }

    @Step("Check count of elements on Different Elements page")
    public void checkDifferentElementsCount() {
        softAssert.assertEquals(differentElementsPage.getCheckBoxes().size(), Element.values().length);
        softAssert.assertEquals(differentElementsPage.getRadioButtons().size(), Metal.values().length);
        softAssert.assertEquals(differentElementsPage.getButtons().size(), Button.values().length);
        checkElementIsDisplayed(differentElementsPage.getColorsDropDown());
    }

    @Step("Check log sidebar exists")
    public void checkLogSideBar() {
        checkElementIsDisplayed(differentElementsPage.getLogSideBar());
    }

    @Step("Check navigation bar exists")
    public void checkNavigationSideBar() {
        checkElementIsDisplayed(differentElementsPage.getNavigationSideBar());
    }

    @Step("Tick checkbox and check log")
    public void selectCheckBoxAndCheckLog(Element checkBox) {
        String name = checkBox.getName();
        findElementByLabelAndClick(differentElementsPage.getCheckBoxes(), name);
        softAssert.assertTrue(getLastLogRecord().endsWith(name + ": condition changed to true"));
    }

    @Step("Select radiobutton and check log")
    public void selectRadioButtonAndCheckLog(Metal button) {
        String name = button.getName();
        findElementByLabelAndClick(differentElementsPage.getRadioButtons(), name);
        softAssert.assertTrue(getLastLogRecord().contains("metal: value changed to " + name));
    }

    @Step("Select dropdown and check log")
    public void selectColorsDropDownAndCheckLog(Color color) {
        WebElement colorsDropDown = differentElementsPage.getColorsDropDown();
        colorsDropDown.click();
        Select dropDownColor = new Select(colorsDropDown);
        String colorText = color.getText();
        dropDownColor.selectByVisibleText(colorText);
        softAssert.assertTrue(getLastLogRecord().contains("Colors: value changed to " + colorText));
    }

    @Step("Deselect all elements and check log")
    public void deselectCheckBoxAndCheckLog(Element checkBox) {
        String name = checkBox.getName();
        findElementByLabelAndClick(differentElementsPage.getCheckBoxes(), name);
        softAssert.assertTrue(getLastLogRecord().endsWith(name + ": condition changed to false"));
    }

    private void checkAreValidServiceDropDownItems(List<WebElement> items) {
        for (ServiceDropdownItem value : ServiceDropdownItem.values()) {
            checkItemsContain(items, value);
        }
    }

    private void checkItemsContain(List<WebElement> actual, ServiceDropdownItem expected) {
        String expectedText = expected.getText();
        boolean isValid = actual.stream().anyMatch(value -> value.getText().toUpperCase().equals(expectedText));
        softAssert.assertTrue(isValid, String.format("'%s': ", expectedText));
    }

    private void findElementByLabelAndClick(List<WebElement> elements, String name) {
        elements.stream().filter(e -> e.getText().equals(name)).findFirst().get().click();
    }

    private String getLastLogRecord() {
        return differentElementsPage.getLogArea().getText();
    }
}
