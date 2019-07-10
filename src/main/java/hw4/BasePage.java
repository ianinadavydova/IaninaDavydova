package hw4;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    public SelenideElement getTitle() {
        return $("title");
    }
}
