package hw4.ex1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import hw3.enums.ServiceDropdownItem;
import hw4.BaseTestHw4;
import hw4.HomePage;
import hw4.TableWithPagesPage;
import hw4.enums.TableWithPagesLength;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.assertTrue;

public class Exercise1 extends BaseTestHw4 {
    @Test
    public void Exercise1Test() {
        // Step #1 Open test site by URL, test site is opened
        // Step #2 Assert Browser title
        // Step #3 Perform login
        HomePage homePage = getHomePage();

        // Step #4 Assert User name in the left-top side of screen that user is loggined
        homePage.getUserName().shouldHave(exactText(getTestUser().getName()));

        // Step #5 Click on "Service" subcategory in the header and check that drop down contains options
        homePage.getService().click();
        checkAreValidServiceDropDownItems(homePage.getServiceDropdownItems());

        // Step #6 Click on "Service" subcategory in the left section and check that drop down contains options
        homePage.getLeftService().click();
        checkAreValidServiceDropDownItems(homePage.getLeftServiceDropdownItems());

        // Step #7 Open through the header menu Service -> Table with pages
        homePage.getService().click();
        homePage.getTablePagesItem().click();

        // TODO Could be used Selenide.page() instead of createPage(TableWithPagesPage.class)
        checkTableWithPages(createPage(TableWithPagesPage.class));
    }

    private static void checkAreValidServiceDropDownItems(ElementsCollection items) {
        for (ServiceDropdownItem value : ServiceDropdownItem.values()) {
            checkItemsContain(items, value);
        }
    }

    private static void checkItemsContain(ElementsCollection actual, ServiceDropdownItem expected) {
        String expectedText = expected.getText();
        boolean isValid = actual.stream().anyMatch(value -> value.getText().toUpperCase().equals(expectedText));
        assertTrue(isValid, String.format("'%s': ", expectedText));
    }

    private static void checkTableWithPages(TableWithPagesPage page) {
        // Step #8 Check that default value for “Show entries” dropdown is 5
        SelenideElement tableLength = page.getTableWithPagesLength();
        checkComboBoxSelection(tableLength, TableWithPagesLength.DEFAULT);

        // Step #9 Assert that there is Right Section
        page.getRightSection().shouldBe(visible);

        // Step #10 Assert that there is Left Section
        page.getLeftSection().shouldBe(visible);

        // Step #11 Select new value for the entries in the dropdown list
        checkComboBoxSelection(tableLength, TableWithPagesLength.TEN);

        // Step #12 Assert that for the dropdown there is an individual log row and value is corresponded to the value of dropdown. 
        ElementsCollection logRecords = page.getLogRecords();
        logRecords.findBy(correspondTo(TableWithPagesLength.TEN)).shouldBe(visible);

        // Step #13 Assert that in the table displayed corrected amount of entries
        page.getTableRows().shouldHaveSize(TableWithPagesLength.TEN.getValue());

        // Step #14 Type in “Search” text field
        // Step #15 Assert the table contains only records with Search field value
        checkSearch(page, "Custom");
    }

    private static void checkComboBoxSelection(SelenideElement comboBox, TableWithPagesLength length) {
        comboBox.selectOptionByValue(Integer.toString(length.getValue()));
        checkSelectedValue(comboBox, length);
    }

    private static void checkSelectedValue(SelenideElement comboBox, TableWithPagesLength expected) {
        comboBox.getSelectedOption().shouldHave(exactText(Integer.toString(expected.getValue())));
    }

    private static Condition correspondTo(TableWithPagesLength length) {
        return matchesText(String.format(".*new value=%s$", length.getValue()));
    }

    private static void checkSearch(TableWithPagesPage tableWithPages, String filter) {
        tableWithPages.getTableWithPagesFilter().setValue(filter);

        for (SelenideElement tableRow : tableWithPages.getTableRows()) {
            SelenideElement cell = tableRow.find(By.tagName("td"), 1);
            cell.should(matchesText(String.format(".*%s.*", filter)));
        }
    }
}
