package hw1;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AdditionTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "long input")
    public void addLong(long a, long b) {
        assertEquals(calc.sum(a, b), a + b);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "double input")
    public void addDouble(double a, double b) {
        assertEquals(calc.sum(a, b), a + b);
    }
}
