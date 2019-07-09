package hw6.steps;

import com.codeborne.selenide.ElementsCollection;
import cucumber.api.java.en.Then;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AssertionsSteps extends StepBackground {
    @Then("^Title should be '([^\"]*)'")
    public void checkHomePageTitle(String title) {
        homePage.getTitle().shouldHave(attribute("text", title));
    }

    @Then("^User name should be '([^\"]*)'")
    public void checkUsername(String username) {
        homePage.getUserName().shouldHave(exactText(username));
    }

    @Then("^Page contains (\\d+) images$")
    public void checkHomePageContainsImages(int expectedCount) {
        homePage.getImages().shouldHaveSize(expectedCount);
    }

    @Then("^Page contains (\\d+) text labels$")
    public void checkHomePageContainsTextLabels(int expectedCount) {
        homePage.getImageTexts().shouldHaveSize(expectedCount);
    }

    @Then("^Page contains headline$")
    public void checkHomePageContainsHeadline() {
        homePage.getHeadline().shouldBe(visible);
    }

    @Then("^Page contains description$")
    public void checkHomePageContainsDescription() {
        homePage.getDescription().shouldBe(visible);
    }

    @Then("^Header 'Service' drop down contains the following options:$")
    public void serviceDropDownContains(List<String> expected) {
        checkAreValidServiceDropDownItems(homePage.getServiceDropdownItems(), expected);
    }

    @Then("^Left section 'Service' drop down contains the following options:$")
    public void leftServiceDropDownContains(List<String> expected) {
        checkAreValidServiceDropDownItems(homePage.getLeftServiceDropdownItems(), expected);
    }

    @Then("^Page contains (\\d+) checkboxes$")
    public void checkDiffElementsPageContainsCheckboxes(int expectedCount) {
        assertEquals(differentElementsPage.getCheckBoxes().size(), expectedCount);
    }

    @Then("^Page contains (\\d+) radios$")
    public void checkDiffElementsPageContainsRadios(int expectedCount) {
        assertEquals(differentElementsPage.getRadioButtons().size(), expectedCount);
    }

    @Then("^Page contains (\\d+) buttons$")
    public void checkDiffElementsPageContainsButtons(int expectedCount) {
        assertEquals(differentElementsPage.getButtons().size(), expectedCount);
    }

    @Then("^Page contains 1 dropdown$")
    public void checkDiffElementsPageContainsDropdown() {
        assertTrue(differentElementsPage.getColorsDropDown().isDisplayed());
    }

    @Then("^Page contains right section$")
    public void checkDiffElementsPageContainsRightSection() {
        assertTrue(differentElementsPage.getLogSideBar().isDisplayed());
    }

    @Then("^Page contains left section$")
    public void checkDiffElementsPageContainsLeftSection() {
        assertTrue(differentElementsPage.getNavigationSideBar().isDisplayed());
    }

    @Then("^log row with value corresponded to element \"([^\"]*)\" is displayed$")
    public void checkLogRowAfterConditionSelection(String condition) {
        checkLogRecordForCondition(condition, true);
    }

    @Then("^log row with value corresponded to element \"([^\"]*)\" deselection is displayed$")
    public void checkLogRowAfterConditionDeselection(String condition) {
        checkLogRecordForCondition(condition, false);
    }

    @Then("^log row with value corresponded to metal \"([^\"]*)\" is displayed$")
    public void checkLogRowAfterMetalSelection(String metal) {
        assertTrue(getLastLogRecord().contains("metal: value changed to " + metal));
    }

    @Then("^log row with value corresponded to color \"([^\"]*)\" is displayed$")
    public void checkLogRowAfterColorSelection(String color) {
        assertTrue(getLastLogRecord().contains("Colors: value changed to " + color));
    }

    private static void checkAreValidServiceDropDownItems(ElementsCollection actual, List<String> expected) {
        for (String value : expected) {
            checkItemsContain(actual, value);
        }
    }

    private static void checkItemsContain(ElementsCollection actual, String expectedText) {
        boolean isValid = actual.stream().anyMatch(value -> value.getText().equalsIgnoreCase(expectedText));
        assertTrue(isValid, String.format("'%s': ", expectedText));
    }

    private void checkLogRecordForCondition(String condition, boolean isSelected) {
        String expected = String.format("%s: condition changed to %b", condition, isSelected);
        assertTrue(getLastLogRecord().endsWith(expected));
    }

    private String getLastLogRecord() {
        return differentElementsPage.getLogArea().getText();
    }
}
