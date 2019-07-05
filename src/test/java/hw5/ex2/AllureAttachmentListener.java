package hw5.ex2;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class AllureAttachmentListener extends TestListenerAdapter {

    @Attachment(value = "Attachment: {0}", type = "image/png")
    public byte[] makeScreenshot(String name) {
        byte[] array = {1};
        try {
            WebDriver driver = TestProvider.getInstance().getDriver();
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return array;
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        makeScreenshot("Failed");
    }
}
