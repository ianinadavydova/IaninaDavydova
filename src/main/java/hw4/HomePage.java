package hw4;

import com.codeborne.selenide.SelenideElement;
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

    @FindBy(id = "user-name")
    private SelenideElement userName;

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
