package hw3.voids;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage  {
    @FindBy(id = "user-icon")
    private WebElement userIcon;

    @FindBy(id = "name")
    private WebElement loginNameTextField;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@id='login-button']")
    private WebElement loginButton;

    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(css = ".nav>li>a")
    private List<WebElement> navBarItems;

    @FindBy(css = ".benefit-icon")
    private List<WebElement> images;

    @FindBy(css = ".benefit-txt")
    private List<WebElement> imageTexts;

    @FindBy(css = "[name='main-title']")
    private WebElement mainHeader;

    @FindBy(css = "[name='jdi-text']")
    private WebElement jdiText;

    private static final String IFRAME_ID = "iframe";

    @FindBy(id = IFRAME_ID)
    private WebElement iframe;

    @FindBy(css = "h3 a")
    private WebElement subHeader;

    @FindBy(id = "mCSB_1")
    private WebElement leftSection;

    @FindBy(tagName = "footer")
    private WebElement footer;

    @FindBy(xpath = "//ul[@class='uui-navigation nav navbar-nav m-l8']//li//a[contains(.,'Service')]")
    private WebElement service;

    @FindBy(xpath = "//ul[@class='sidebar-menu']//li//a[contains(.,'Service')]")
    private WebElement leftService;

    @FindBy(css = "ul .dropdown-menu li")
    private List<WebElement> serviceDropdownItems;

    @FindBy(xpath = "//ul[@class='sub']//li//a")
    private List<WebElement> leftServiceDropdownItems;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//li//a[@href='different-elements.html']")
    private WebElement differentElementsItem;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void login(String userName, String password) {
        userIcon.click();
        loginNameTextField.sendKeys(userName);
        this.password.sendKeys(password);
        loginButton.click();
    }

    public String getUserName() {
        return userName.getText();
    }

    public List<WebElement> getNavBarItems() {
        return navBarItems;
    }

    public List<WebElement> getImages() {
        return images;
    }

    public List<WebElement> getImageTexts() {
        return imageTexts;
    }

    public WebElement getMainHeader() {
        return mainHeader;
    }

    public WebElement getJdiText() {
        return jdiText;
    }

    public WebElement getIFrame() {
        return iframe;
    }

    public WebElement getEpamLogo() {
        driver.switchTo().frame(IFRAME_ID);
        IFrame frame = new IFrame(driver);
        driver.switchTo().defaultContent();
        return frame.logo;
    }

    public WebElement getSubHeader() {
        return subHeader;
    }

    public WebElement getLeftSection() {
        return leftSection;
    }

    public WebElement getFooter() {
        return footer;
    }

    public WebElement getService() {
        return service;
    }

    public WebElement getLeftService() {
        return leftService;
    }

    public List<WebElement> getServiceDropdownItems() {
        return serviceDropdownItems;
    }

    public List<WebElement> getLeftServiceDropdownItems() {
        return leftServiceDropdownItems;
    }

    public WebElement getDifferentElementsItem() {
        return differentElementsItem;
    }

    private static class IFrame {
        @FindBy(id = "epam_logo")
        public WebElement logo;

        public IFrame(WebDriver driver) {
            PageFactory.initElements(driver, this);
        }
    }
}
