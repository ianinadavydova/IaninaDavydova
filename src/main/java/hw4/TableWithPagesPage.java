package hw4;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

public class TableWithPagesPage {
    @Getter
    @FindBy(name = "table-with-pages_length")
    private SelenideElement tableWithPagesLength;

    @Getter
    @FindBy(css = "#table-with-pages_filter input")
    private SelenideElement tableWithPagesFilter;

    @Getter
    @FindBy(id = "mCSB_1")
    private SelenideElement leftSection;

    @Getter
    @FindBy(name = "log-sidebar")
    private SelenideElement rightSection;

    @Getter
    @FindBy(css = ".panel-body-list.logs > li")
    private ElementsCollection logRecords;

    @Getter
    @FindBy(css = "tbody > tr")
    private ElementsCollection tableRows;
}
