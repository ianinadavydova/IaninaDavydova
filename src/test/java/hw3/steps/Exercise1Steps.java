package hw3.steps;

import hw3.voids.HomePage;
import org.openqa.selenium.WebDriver;

public class Exercise1Steps extends BaseSteps {

    protected HomePage homePage;

    public Exercise1Steps(WebDriver driver) {
        homePage = new HomePage(driver);
    }
}
