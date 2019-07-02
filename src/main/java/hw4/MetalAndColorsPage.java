package hw4;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

public class MetalAndColorsPage {
    @Getter
    @FindBy(css = "#even-selector p")
    private ElementsCollection summaryEven;

    @Getter
    @FindBy(css = "#odds-selector p")
    private ElementsCollection summaryOdd;

    @Getter
    @FindBy(css = "#elements-checklist p")
    private ElementsCollection elements;

    @Getter
    @FindBy(css = ".colors button")
    private SelenideElement colorsButton;

    @Getter
    @FindBy(css = ".colors ul > li")
    private ElementsCollection colorOptions;

    @Getter
    @FindBy(css = ".metals .caret")
    private SelenideElement metalsButton;

    @Getter
    @FindBy(css = ".metals ul > li")
    private ElementsCollection metalOptions;

    @Getter
    @FindBy(css = "#vegetables button")
    private SelenideElement vegetablesButton;

    @Getter
    @FindBy(css = "#vegetables ul > li")
    private ElementsCollection vegetableOptions;

    @Getter
    @FindBy(id = "submit-button")
    private SelenideElement submit;

    @Getter
    @FindBy(css = ".info-panel-body-result ul > li")
    private ElementsCollection results;
}
