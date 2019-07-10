package hw6.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ActionSteps extends StepBackground {
    @When("^I login as user '([^\"]*)' with password '([^\"]*)'$")
    public void loginAsUserWithPassword(String username, String password) {
        homePage.login(username, password);
    }

    @When("^I click on \"Service\" button in Header$")
    public void clickOnServiceSubcategoryInTheHeader() {
        homePage.getService().click();
    }

    @When("^I click on \"User Table\" button in Service dropdown$")
    public void clickOnUserTableButton() {
        homePage.getUserTableItem().click();
    }

    @When("^I click on \"Service\" subcategory in the left section$")
    public void clickOnServiceSubcategoryInTheLeftSection() {
        homePage.getLeftService().click();
    }

    @When("^I select checkbox \"([^\"]*)\"$")
    public void selectCheckbox(String name) {
        findElementByLabelAndClick(differentElementsPage.getCheckBoxes(), name);
    }

    @When("^I select radio button \"([^\"]*)\"$")
    public void selectRadioButton(String name) {
        findElementByLabelAndClick(differentElementsPage.getRadioButtons(), name);
    }

    @When("^I select dropdown \"([^\"]*)\"$")
    public void selectColorsDropdown(String color) {
        WebElement colorsDropDown = differentElementsPage.getColorsDropDown();
        colorsDropDown.click();
        Select dropDownColor = new Select(colorsDropDown);
        dropDownColor.selectByVisibleText(color);
    }

    @When("^I select 'vip' checkbox for \"([^\"]*)\"$")
    public void selectVipCheckbox(String username) {
        userTablePage.getCheckboxes().get(getRowIndexByUserName(username)).setSelected(true);
    }

    @When("^I click on dropdown in column Type for user ([^\"]*)$")
    public void clickOnDropdownInColumnTypeForUser(String username) {
        userTablePage.getDropdowns().get(getRowIndexByUserName(username)).click();
    }

    private int getRowIndexByUserName(String username) {
        ElementsCollection userNames = userTablePage.getUserNames();
        SelenideElement row = userNames.findBy(Condition.exactText(username));
        return userNames.indexOf(row);
    }

    private void findElementByLabelAndClick(List<WebElement> elements, String name) {
        elements.stream().filter(e -> e.getText().equals(name)).findFirst().get().click();
    }
}
