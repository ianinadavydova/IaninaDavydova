package hw6;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import hw4.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class UserTablePage extends BasePage {
    @Getter
    @FindBy(xpath = "//select")
    private ElementsCollection dropdowns;

    @Getter
    @FindBy(css = "#user-table tr td:first-child")
    private ElementsCollection numbers;

    @Getter
    @FindBy(css = "#user-table a")
    private ElementsCollection userNames;

    @Getter
    @FindBy(css = "#user-table a")
    private ElementsCollection descriptionImages;

    @Getter
    @FindBy(css = "#user-table .user-descr span")
    private ElementsCollection descriptionTexts;

    @Getter
    @FindBy(css = "#user-table .user-descr input[type|=\"checkbox\"]")
    private ElementsCollection checkboxes;

    @Getter
    @FindBy(css = ".panel-body-list.logs > li")
    private SelenideElement lastLogRecord;

    @FindBy(css = "#user-table tr select")
    private SelenideElement type;

    public ElementsCollection getTypeOptions() {
        return type.findAll(By.tagName("option"));
    }
}
