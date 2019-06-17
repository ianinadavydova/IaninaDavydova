package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DivisionTest {

    // TODO It will be better extract this field and BeforeHook to some base class
    private Calculator calc;

    // TODO It will be better used BeforeMethod Hook here
    @BeforeSuite
    public void createCalc() {
        calc = new Calculator();
    }

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
