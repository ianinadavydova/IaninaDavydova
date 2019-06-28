package hw3.steps;

import hw3.enums.*;
import hw3.voids.DifferentElementsPage;
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

    public void checkService() {
        homePage.getService().click();
        checkAreValidServiceDropDownItems(homePage.getServiceDropdownItems());
    }

    public void checkLeftService() {
        homePage.getLeftService().click();
        checkAreValidServiceDropDownItems(homePage.getLeftServiceDropdownItems());
    }

    public void openDifferentElementsPage() {
        homePage.getService().click();
        homePage.getDifferentElementsItem().click();
        softAssert.assertEquals(differentElementsPage.getTitle(), Page.DIFFERENT_ELEMENTS.getTitle());
    }

    public void checkDifferentElementsCount() {
        softAssert.assertEquals(differentElementsPage.getCheckBoxes().size(), CheckBox.values().length);
        softAssert.assertEquals(differentElementsPage.getRadioButtons().size(), RadioButton.values().length);
        softAssert.assertEquals(differentElementsPage.getButtons().size(), Button.values().length);
        checkElementIsDisplayed(differentElementsPage.getColorsDropDown());
    }

    public void checkLogSideBar() {
        checkElementIsDisplayed(differentElementsPage.getLogSideBar());
    }

    public void checkNavigationSideBar() {
        checkElementIsDisplayed(differentElementsPage.getNavigationSideBar());
    }

    public void selectCheckBoxAndCheckLog(CheckBox checkBox) {
        String name = checkBox.getName();
        findElementByLabelAndClick(differentElementsPage.getCheckBoxes(), name);
        softAssert.assertTrue(getLastLogRecord().endsWith(name + ": condition changed to true"));
    }

    public void selectRadioButtonAndCheckLog(RadioButton button) {
        String name = button.getName();
        findElementByLabelAndClick(differentElementsPage.getRadioButtons(), name);
        softAssert.assertTrue(getLastLogRecord().contains("metal: value changed to " + name));
    }

    public void selectColorsDropDownAndCheckLog(Color color) {
        WebElement colorsDropDown = differentElementsPage.getColorsDropDown();
        colorsDropDown.click();
        Select dropDownColor = new Select(colorsDropDown);
        String colorText = color.getText();
        dropDownColor.selectByVisibleText(colorText);
        softAssert.assertTrue(getLastLogRecord().contains("Colors: value changed to " + colorText));
    }

    public void deselectCheckBoxAndCheckLog(CheckBox checkBox) {
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
