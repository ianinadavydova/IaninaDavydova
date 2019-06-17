package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SubstractionTest {

    // TODO It will be better extract this field and BeforeHook to some base class
    private Calculator calc;

    // TODO It will be better used BeforeMethod Hook here
    @BeforeSuite
    public void createCalc() {
        calc = new Calculator();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "long input")
    public void substractLong(long a, long b) {
        assertEquals(calc.sub(a, b), a - b);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "double input")
    public void substractDouble(double a, double b) {
        assertEquals(calc.sub(a, b), a - b);
    }
}
