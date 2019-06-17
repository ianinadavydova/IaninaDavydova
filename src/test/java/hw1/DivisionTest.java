package hw1;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DivisionTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "long input")
    public void divideLong(long a, long b) {
        if (b == 0) return;
        assertEquals(calc.div(a, b), a / b);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "double input")
    public void divideDouble(double a, double b) {
        if (b == 0) return;
        assertEquals(calc.div(a, b), a / b);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void divideByZero() {
        calc.div(1, 0);
    }
}
