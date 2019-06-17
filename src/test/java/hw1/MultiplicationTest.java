package hw1;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MultiplicationTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "long input")
    public void multiplyLong(long a, long b) {
        assertEquals(calc.mult(a, b), a * b);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "double input")
    public void multiplyDouble(double a, double b) {
        assertEquals(calc.mult(a, b), a * b);
    }
}
