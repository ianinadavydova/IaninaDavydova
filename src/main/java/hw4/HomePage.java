package hw4;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    @FindBy(id = "user-icon")
    private SelenideElement userIcon;

    @FindBy(id = "name")
    private SelenideElement loginNameTextField;

    @FindBy(id = "password")
    private SelenideElement password;

    @FindBy(xpath = "//button[@id='login-button']")
    private SelenideElement loginButton;

    @Getter
    @FindBy(id = "user-name")
    private SelenideElement userName;

    @Getter
    @FindBy(xpath = "//ul[@class='uui-navigation nav navbar-nav m-l8']//li//a[contains(.,'Service')]")
    private SelenideElement service;

    @Getter
    @FindBy(xpath = "//ul[@class='uui-navigation nav navbar-nav m-l8']//li//a[contains(.,'Metals & Colors')]")
    private SelenideElement metalsAndColors;

    @Getter
    @FindBy(css = "ul .dropdown-menu li")
    private ElementsCollection serviceDropdownItems;

    @Getter
    @FindBy(xpath = "//ul[@class='sidebar-menu']//li//a[contains(.,'Service')]")
    private SelenideElement leftService;

    @Getter
    @FindBy(xpath = "//ul[@class='sub']//li//a")
    private ElementsCollection leftServiceDropdownItems;

    @Getter
    @FindBy(xpath = "//ul[@class='dropdown-menu']//li//a[@href='table-pages.html']")
    private SelenideElement tablePagesItem;

    public SelenideElement getTitle() {
        return $("title");
    }

    private HomePage typeUserName(String value) {
        loginNameTextField.sendKeys(value);
        return this;
    }

    private HomePage typePassword(String value) {
        password.sendKeys(value);
        return this;
    }

    public HomePage login(User user) {
        userIcon.click();
        typeUserName(user.getUserName()).typePassword(user.getPassword());
        loginButton.click();
        return this;
    }
}
