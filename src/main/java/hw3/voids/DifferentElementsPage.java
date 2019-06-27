package hw3.voids;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DifferentElementsPage extends BasePage {
    @FindBy(className = "label-checkbox")
    private List<WebElement> checkBoxes;

    @FindBy(className = "label-radio")
    private List<WebElement> radioButtons;

    @FindBy(css = ".uui-button[value]")
    private List<WebElement> buttons;

    @FindBy(css = ".colors .uui-form-element")
    private WebElement colorsDropDown;

    @FindBy(name = "log-sidebar")
    private WebElement logSideBar;

    @FindBy(name = "navigation-sidebar")
    private WebElement navigationSideBar;

    @FindBy(css = ".panel-body-list.logs > li")
    private WebElement logArea;

    public DifferentElementsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getCheckBoxes() {
        return checkBoxes;
    }

    public List<WebElement> getRadioButtons() {
        return radioButtons;
    }

    public List<WebElement> getButtons() {
        return buttons;
    }

    public WebElement getColorsDropDown() {
        return colorsDropDown;
    }

    public WebElement getLogSideBar() {
        return logSideBar;
    }

    public WebElement getNavigationSideBar() {
        return navigationSideBar;
    }

    public WebElement getLogArea() {
        return logArea;
    }
}
