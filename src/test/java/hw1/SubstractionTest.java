package hw1;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SubstractionTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "long input")
    public void substractLong(long a, long b) {
        assertEquals(calc.sub(a, b), a - b);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "double input")
    public void substractDouble(double a, double b) {
        assertEquals(calc.sub(a, b), a - b);
    }
}
