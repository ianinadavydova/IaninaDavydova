package hw6.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import cucumber.api.java.en.Then;
import hw4.BasePage;
import hw4.HomePage;
import hw6.UserTablePage;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AssertionsSteps extends StepBackground {
    @Then("^Title should be '([^\"]*)'")
    public void checkHomePageTitle(String title) {
        checkPageTitle(homePage, title);
    }

    @Then("^User name should be '([^\"]*)'")
    public void checkUsername(String username) {
        checkUsername(homePage, username);
    }

    static void checkUsername(HomePage homePage, String username) {
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

    @Then("^Header \"Service\" drop down contains the following options:$")
    public void serviceDropDownContains(List<String> expected) {
        checkAreValidServiceDropDownItems(homePage.getServiceDropdownItems(), expected);
    }

    @Then("^Left section \"Service\" drop down contains the following options:$")
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

    @Then("\"([^\"]*)\" page is opened$")
    public void checkTitle(String title) {
        userTablePage = Selenide.page(UserTablePage.class);
        checkPageTitle(userTablePage, title);
    }

    @Then("^(\\d+) NumberType Dropdowns are displayed on Users Table on User Table Page$")
    public void checkDropdowns(int expectedCount) {
        userTablePage.getDropdowns().shouldHaveSize(expectedCount);
    }

    @Then("^(\\d+) User names are displayed on Users Table on User Table Page$")
    public void checkUserNames(int expectedCount) {
        userTablePage.getUserNames().shouldHaveSize(expectedCount);
    }

    @Then("^(\\d+) Description images are displayed on Users Table on User Table Page$")
    public void checkDescriptionImages(int expectedCount) {
        userTablePage.getDescriptionImages().shouldHaveSize(expectedCount);
    }

    @Then("^(\\d+) Description texts under images are displayed on Users Table on User Table Page$")
    public void checkDescriptionTexts(int expectedCount) {
        userTablePage.getDescriptionTexts().shouldHaveSize(expectedCount);
    }

    @Then("^(\\d+) checkboxes are displayed on Users Table on User Table Page$")
    public void checkCheckboxes(int expectedCount) {
        userTablePage.getCheckboxes().shouldHaveSize(expectedCount);
    }

    @Then("^User table contains following values:$")
    public void checkTableContent(List<Map<String, String>> expected) {
        compareCollectionAndTableColumn(userTablePage.getNumbers(),          expected, "Number");
        compareCollectionAndTableColumn(userTablePage.getUserNames(),        expected, "User");
        compareCollectionAndTableColumn(userTablePage.getDescriptionTexts(), expected, "Description");
    }

    @Then("^1 log row has \"([^\"]*)\" text in log section$" )
    public void checkLogRecord(String expected) {
        userTablePage.getLastLogRecord().should(matchText(String.format(".*%s$", expected)));
    }

    @Then("droplist contains values")
    public void checkDropDownItems(List<Map<String, String>> expected) {
        compareCollectionAndTableColumn(userTablePage.getTypeOptions(), expected, "Dropdown Values");
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

    private static void checkPageTitle(BasePage page, String title) {
        page.getTitle().shouldHave(attribute("text", title));
    }

    private static void compareCollectionAndTableColumn(ElementsCollection actual, List<Map<String, String>> expected, String column) {
        int expectedCount = expected.size();
        actual.shouldHaveSize(expectedCount);
        for (int i = 0; i != expectedCount; ++i) {
            actual.get(i).shouldHave(text(expected.get(i).get(column)));
        }
    }
}
