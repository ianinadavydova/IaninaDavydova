package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MultiplicationTest {

    private Calculator calc;

    @BeforeSuite
    public void createCalc() {
        calc = new Calculator();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "long input")
    public void multiplyLong(long a, long b) {
        assertEquals(calc.mult(a, b), a * b);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "double input")
    public void multiplyDouble(double a, double b) {
        assertEquals(calc.mult(a, b), a * b);
    }
}
