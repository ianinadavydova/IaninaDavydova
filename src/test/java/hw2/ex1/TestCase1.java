package hw2.ex1;

import hw2.BaseTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class TestCase1 extends BaseTest {

    // Step 1 Open test site by URL, test site is opened
    @Test
    public void isCorrectURL(){
        assertEquals(driver.getCurrentUrl(), "https://epam.github.io/JDI/");
    }
}
