package hw6.steps;

import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ActionSteps extends StepBackground {
    @When("^I login as user '([^\"]*)' with password '([^\"]*)'$")
    public void loginAsUserWithPassword(String username, String password) {
        homePage.login(username, password);
    }

    @When("^I click on 'Service' subcategory in the header$")
    public void clickOnServiceSubcategoryInTheHeader() {
        homePage.getService().click();
    }

    @When("^I click on 'Service' subcategory in the left section$")
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

    private void findElementByLabelAndClick(List<WebElement> elements, String name) {
        elements.stream().filter(e -> e.getText().equals(name)).findFirst().get().click();
    }
}
